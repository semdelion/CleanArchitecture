package com.semdelion.presentation.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.generateViewId
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.semdelion.R
import com.semdelion.databinding.FragmentNewsDetailsBinding
import com.semdelion.presentation.viewmodels.NewsDetailsViewModel
import com.semdelion.presentation.views.factories.NewsDetailsViewModelFactory

class NewsDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = NewsDetailsFragment()
    }

    private lateinit var viewModel: NewsDetailsViewModel
    private lateinit var viewBinding: FragmentNewsDetailsBinding
    private val args: NewsDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news_details, container, false)
        viewBinding.lifecycleOwner = this
        viewBinding.vm = viewModel

        if (viewModel.imageUrl.isNotEmpty()) {
            Glide.with(requireContext().applicationContext)
                .load(viewModel.imageUrl)
                .placeholder(R.drawable.ic_news_placeholder)
                .into(viewBinding.newsDetailsImageview)
        } else {
            viewBinding.newsDetailsImageview.visibility = GONE
        }

        for (creator in viewModel.creators) {
            val creatorView = inflater.inflate(
                R.layout._template_creator,
                viewBinding.newsDetailsConstraint,
                false
            )
            creatorView.id = generateViewId()
            creatorView.findViewById<TextView>(R.id.creator).text = creator
            viewBinding.newsDetailsConstraint.addView(creatorView)
            viewBinding.creatorsFlow.addView(creatorView)
        }

        return viewBinding.root;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            NewsDetailsViewModelFactory(args.newsItem)
        )[NewsDetailsViewModel::class.java]
        //TODO https://stackoverflow.com/questions/67350331/how-to-use-hilt-to-inject-a-safe-args-argument-into-a-viewmodel
    }
}