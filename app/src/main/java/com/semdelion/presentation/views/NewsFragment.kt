package com.semdelion.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.semdelion.R
import com.semdelion.databinding.FragmentNewsBinding
import com.semdelion.presentation.navigation.NewsNavigationArg
import com.semdelion.presentation.viewmodels.NewsViewModel
import com.semdelion.presentation.views.adapters.NewsRecyclerAdapter
import com.semdelion.presentation.views.factories.NewsViewModelFactory

class NewsFragment : Fragment() {

    companion object {
        fun newInstance() = NewsFragment()
    }

    private lateinit var viewModel: NewsViewModel
    private lateinit var viewBinding: FragmentNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            NewsViewModelFactory()
        )[NewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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
        viewModel.newsModelItems.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewBinding.newsSwipeRefreshLayout.setOnRefreshListener {
            viewModel.loadNews()
                .invokeOnCompletion { viewBinding.newsSwipeRefreshLayout.isRefreshing = false }
        }

        return viewBinding.root
    }
}