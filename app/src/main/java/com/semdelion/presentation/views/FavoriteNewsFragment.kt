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
            FavoriteNewsViewModelFactory(requireContext().applicationContext)
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
        viewModel.newsModelItems.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewBinding.newsSwipeRefreshLayout.setOnRefreshListener {
            viewModel.loadFavoriteNews()
                .invokeOnCompletion { viewBinding.newsSwipeRefreshLayout.isRefreshing = false }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collectLatest {
                    when(it) {
                        is ListViewState.Loading -> {
                            viewBinding.newsLoaderProgressBar.visibility = View.VISIBLE
                        }
                        else -> {
                            viewBinding.newsLoaderProgressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }

        /*getNavigationResultLiveData<Boolean>()?.observe(viewLifecycleOwner) { booleanValue ->
            if(booleanValue) {
                viewModel.loadFavoriteNews()
                setNavigationResult(false)
            }
        }*/

        return viewBinding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadFavoriteNews()
    }
}
