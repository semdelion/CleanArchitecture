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
import com.semdelion.databinding.FragmentFavoriteNewsBinding
import com.semdelion.presentation.navigation.NewsNavigationArg
import com.semdelion.presentation.viewmodels.FavoriteNewsViewModel
import com.semdelion.presentation.views.adapters.FavoriteNewsRecyclerAdapter
import com.semdelion.presentation.views.factories.FavoriteNewsViewModelFactory

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
