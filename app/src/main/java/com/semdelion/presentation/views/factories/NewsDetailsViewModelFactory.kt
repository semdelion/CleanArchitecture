package com.semdelion.presentation.views.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.semdelion.data.repositories.NewsRepository
import com.semdelion.presentation.navigation.NewsNavigationArg
import com.semdelion.presentation.viewmodels.NewsDetailsViewModel

class NewsDetailsViewModelFactory(val navArg: NewsNavigationArg): ViewModelProvider.Factory  {

    private val newsRepository by lazy {
        NewsRepository()
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsDetailsViewModel::class.java)) {
            return NewsDetailsViewModel(navArg) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}