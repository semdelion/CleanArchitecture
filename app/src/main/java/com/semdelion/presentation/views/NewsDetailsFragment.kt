package com.semdelion.presentation.views

import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.view.View.generateViewId
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.semdelion.R
import com.semdelion.databinding.FragmentNewsDetailsBinding
import com.semdelion.presentation.viewmodels.NewsDetailsViewModel
import com.semdelion.presentation.views.factories.NewsDetailsViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NewsDetailsFragment : Fragment(), MenuProvider {

    companion object {
        fun newInstance() = NewsDetailsFragment()
    }

    private lateinit var viewModel: NewsDetailsViewModel
    private lateinit var viewBinding: FragmentNewsDetailsBinding
    private val args: NewsDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this, NewsDetailsViewModelFactory(args.newsItem, requireContext().applicationContext)
        )[NewsDetailsViewModel::class.java]
        //TODO https://stackoverflow.com/questions/67350331/how-to-use-hilt-to-inject-a-safe-args-argument-into-a-viewmodel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news_details, container, false)
        viewBinding.lifecycleOwner = this
        viewBinding.vm = viewModel
        setupMenu()


        if (viewModel.imageUrl.isNotEmpty()) {
            Glide.with(requireContext().applicationContext).load(viewModel.imageUrl)
                .placeholder(R.drawable.ic_news_placeholder).into(viewBinding.newsDetailsImageview)
        } else {
            viewBinding.newsDetailsImageview.visibility = GONE
        }

        for (creator in viewModel.creators) {
            val creatorView = inflater.inflate(
                R.layout._template_creator, viewBinding.newsDetailsConstraint, false
            )
            creatorView.id = generateViewId()
            creatorView.findViewById<TextView>(R.id.creator).text = creator
            viewBinding.newsDetailsConstraint.addView(creatorView)
            viewBinding.creatorsFlow.addView(creatorView)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.saveNewsState.collectLatest {
                    Toast.makeText(viewBinding.root.context, it, Toast.LENGTH_SHORT).show()
                }
            }
        }

        return viewBinding.root;
    }

    private fun setupMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onPrepareMenu(menu: Menu) {
        // Handle for example visibility of menu items
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.save_news_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        val id = menuItem.itemId
        if (id == R.id.add_to_favorite_news) {
            lifecycleScope.launch {
                viewModel.addToFavoriteNews()
            }
        }
        return false
    }
}