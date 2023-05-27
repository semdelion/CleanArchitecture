package com.semdelion.presentation.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.semdelion.R
import com.semdelion.databinding.FragmentNewsDetailsBinding
import com.semdelion.presentation.viewmodels.NewsDetailsViewModel
import com.semdelion.presentation.viewmodels.NewsViewModel

class NewsDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = NewsDetailsFragment()
    }

    private lateinit var viewModel: NewsDetailsViewModel
    private lateinit var viewBinding: FragmentNewsDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_details, container, false)
        viewBinding.lifecycleOwner = this
        viewBinding.vm = viewModel

        val view = viewBinding.root

        return view;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[NewsDetailsViewModel::class.java]
    }
}