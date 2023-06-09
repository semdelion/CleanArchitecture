package com.semdelion.presentation.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.semdelion.R
import com.semdelion.databinding.FragmentFavoriteNewsBinding
import com.semdelion.databinding.FragmentNewsBinding
import com.semdelion.databinding.FragmentUserBinding
import com.semdelion.presentation.viewmodels.FavoriteNewsViewModel
import com.semdelion.presentation.viewmodels.NewsViewModel
import com.semdelion.presentation.views.adapters.NewsRecyclerAdapter
import com.semdelion.presentation.views.factories.FavoriteNewsViewModelFactory
import com.semdelion.presentation.views.factories.NewsViewModelFactory

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

        val view = viewBinding.root
        val recyclerView: RecyclerView = view.findViewById(R.id.news_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext().applicationContext)
        val adapter = NewsRecyclerAdapter()
        recyclerView.adapter = adapter
        viewModel.newsModelItems.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return view
    }
}
