package com.semdelion.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.semdelion.R
import com.semdelion.data.services.interceptors.NoConnectivityException
import com.semdelion.databinding.FragmentFavoriteNewsBinding
import com.semdelion.presentation.navigation.NewsNavigationArg
import com.semdelion.presentation.viewmodels.FavoriteNewsViewModel
import com.semdelion.presentation.viewmodels.extentions.ListViewState
import com.semdelion.presentation.views.adapters.FavoriteNewsRecyclerAdapter
import com.semdelion.presentation.views.factories.FavoriteNewsViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteNewsFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteNewsFragment()
    }

    private lateinit var viewModel: FavoriteNewsViewModel
    private lateinit var viewBinding: FragmentFavoriteNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            FavoriteNewsViewModelFactory()
        )[FavoriteNewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_favorite_news,
            container,
            false
        )
        viewBinding.lifecycleOwner = this
        viewBinding.vm = viewModel

        viewBinding.newsRecyclerview.layoutManager =
            LinearLayoutManager(requireContext().applicationContext)
        val adapter = FavoriteNewsRecyclerAdapter { navArg: NewsNavigationArg ->
            FavoriteNewsFragmentDirections.actionFavoriteNewsFragmentToFavoriteNewsDetailsFragment(
                navArg
            )
        }
        viewBinding.newsRecyclerview.adapter = adapter
        viewModel.items.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.viewState.collectLatest {
                    val hasItems = ((viewModel.items.value?.size ?: 0) > 0)
                    when (it) {
                        is ListViewState.Loading -> {
                            viewBinding.newsLoaderProgressBar.visibility =
                                if (hasItems) View.GONE else View.VISIBLE
                            viewBinding.stateLayout.visibility = View.GONE
                        }
                        is ListViewState.Success -> {
                            viewBinding.newsLoaderProgressBar.visibility = View.GONE
                            viewBinding.stateLayout.visibility = if (hasItems) View.GONE else View.VISIBLE
                            if (!hasItems) {
                                viewBinding.stateAnimationView.setAnimation(R.raw.anim_no_data)
                                viewBinding.stateTextview.text = "No news!"
                            }
                        }
                        is ListViewState.Error -> {
                            viewBinding.stateLayout.visibility = if (hasItems) View.GONE else View.VISIBLE
                            viewBinding.newsLoaderProgressBar.visibility = View.GONE
                            if (!hasItems) {
                                val animId =
                                    if (it.error is NoConnectivityException) R.raw.anim_no_internet
                                    else R.raw.anim_error
                                viewBinding.stateAnimationView.setAnimation(animId)
                                viewBinding.stateTextview.text = it.error.message
                            }
                        }
                    }
                }
            }
        }
        return viewBinding.root
    }
}
