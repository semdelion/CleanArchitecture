package com.semdelion.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
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
import com.semdelion.databinding.FragmentNewsBinding
import com.semdelion.presentation.navigation.NewsNavigationArg
import com.semdelion.presentation.viewmodels.NewsViewModel
import com.semdelion.presentation.viewmodels.extentions.ListViewState
import com.semdelion.presentation.views.adapters.NewsRecyclerAdapter
import com.semdelion.presentation.views.factories.NewsViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NewsFragment : Fragment() {

    companion object {
        fun newInstance() = NewsFragment()
    }

    private lateinit var viewModel: NewsViewModel
    private lateinit var viewBinding: FragmentNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this, NewsViewModelFactory()
        )[NewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        viewBinding.lifecycleOwner = this
        viewBinding.vm = viewModel

        viewBinding.newsRecyclerview.layoutManager =
            LinearLayoutManager(requireContext().applicationContext)
        val adapter = NewsRecyclerAdapter { navArg: NewsNavigationArg ->
            NewsFragmentDirections.actionNewsFragmentToNewsDetailsFragment(
                navArg
            )
        }
        viewBinding.newsRecyclerview.adapter = adapter
        viewModel.items.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewBinding.newsSwipeRefreshLayout.setOnRefreshListener {
            viewModel.loadNews()
                .invokeOnCompletion { viewBinding.newsSwipeRefreshLayout.isRefreshing = false }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.viewState.collectLatest {
                    val hasItems = ((viewModel.items.value?.size ?: 0) > 0)
                    when (it) {
                        is ListViewState.Loading -> {
                            viewBinding.newsLoaderProgressBar.visibility =
                                if (hasItems) GONE else VISIBLE
                            viewBinding.stateLayout.visibility = GONE
                        }
                        is ListViewState.Success -> {
                            viewBinding.newsLoaderProgressBar.visibility = GONE
                            viewBinding.stateLayout.visibility = if (hasItems) GONE else VISIBLE
                            if (!hasItems) {
                                viewBinding.stateAnimationView.setAnimation(R.raw.anim_no_data)
                                viewBinding.stateTextview.text = "No news!"
                            }
                        }
                        is ListViewState.Error -> {
                            viewBinding.stateLayout.visibility = if (hasItems) GONE else VISIBLE
                            viewBinding.newsLoaderProgressBar.visibility = GONE
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