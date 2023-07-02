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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.semdelion.R
import com.semdelion.data.services.interceptors.NoConnectivityException
import com.semdelion.databinding.FragmentFavoriteNewsBinding
import com.semdelion.domain.models.NewsModel
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
    private lateinit var binding: FragmentFavoriteNewsBinding

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
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_favorite_news,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.vm = viewModel

        binding.newsRecyclerview.layoutManager =
            LinearLayoutManager(requireContext().applicationContext)

        val adapter = FavoriteNewsRecyclerAdapter (::onItemClick )
        binding.newsRecyclerview.adapter = adapter
        viewModel.items.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.viewState.collectLatest {
                    stateChangeListener(it)
                }
            }
        }
        return binding.root
    }

    private fun onItemClick(news: NewsModel) {
        val navArg = NewsNavigationArg(
            title = news.title,
            link = news.link,
            creator = news.creator,
            content = news.content,
            pubDate = news.pubDate,
            imageURL = news.imageURL
        )
        this.findNavController().navigate(
            FavoriteNewsFragmentDirections.actionFavoriteNewsFragmentToFavoriteNewsDetailsFragment(
                navArg
            )
        )
    }

    private fun stateChangeListener(state: ListViewState) {
        val hasItems = ((viewModel.items.value?.size ?: 0) > 0)
        when (state) {
            is ListViewState.Loading -> {
                binding.newsLoaderProgressBar.visibility =
                    if (hasItems) View.GONE else View.VISIBLE
                binding.stateLayout.visibility = View.GONE
            }

            is ListViewState.Success -> {
                binding.newsLoaderProgressBar.visibility = View.GONE
                binding.stateLayout.visibility = if (hasItems) View.GONE else View.VISIBLE
                if (!hasItems) {
                    binding.stateAnimationView.setAnimation(R.raw.anim_no_data)
                    binding.stateTextview.text = "No news!"
                }
            }

            is ListViewState.Error -> {
                binding.stateLayout.visibility = if (hasItems) View.GONE else View.VISIBLE
                binding.newsLoaderProgressBar.visibility = View.GONE
                if (!hasItems) {
                    val animId =
                        if (state.error is NoConnectivityException) R.raw.anim_no_internet
                        else R.raw.anim_error
                    binding.stateAnimationView.setAnimation(animId)
                    binding.stateTextview.text = state.error.message
                }
            }
        }
    }
}
