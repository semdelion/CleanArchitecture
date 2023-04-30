package com.semdelion.presentation.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.semdelion.R
import com.semdelion.databinding.FragmentFavoriteNewsBinding
import com.semdelion.databinding.FragmentUserBinding
import com.semdelion.presentation.viewmodels.FavoriteNewsViewModel
import com.semdelion.presentation.viewmodels.NewsViewModel

class FavoriteNewsFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteNewsFragment()
    }

    private lateinit var viewModel: FavoriteNewsViewModel
    private lateinit var viewBinding: FragmentFavoriteNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[FavoriteNewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite_news, container, false)
        viewBinding.lifecycleOwner = this
        viewBinding.vm = viewModel

        val view = viewBinding.root

        return view
    }
}