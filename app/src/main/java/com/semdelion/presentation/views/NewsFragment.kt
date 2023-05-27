package com.semdelion.presentation.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.semdelion.R
import com.semdelion.databinding.FragmentNewsBinding
import com.semdelion.databinding.FragmentUserBinding
import com.semdelion.domain.models.News
import com.semdelion.presentation.viewmodels.FavoriteNewsViewModel
import com.semdelion.presentation.viewmodels.MainViewModel
import com.semdelion.presentation.viewmodels.NewsViewModel
import com.semdelion.presentation.views.adapters.NewsRecyclerAdapter

class NewsFragment : Fragment() {

    companion object {
        fun newInstance() = NewsFragment()
    }

    private lateinit var viewModel: NewsViewModel
    private lateinit var viewBinding: FragmentNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this,
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

        val view = viewBinding.root

        val recyclerView: RecyclerView = view.findViewById(R.id.news_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext().applicationContext)
        val adapter = NewsRecyclerAdapter()
        recyclerView.adapter = adapter
        viewModel.newsItems.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        return view
    }
}