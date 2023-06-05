package com.semdelion.presentation.views.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.semdelion.data.repositories.FavoriteNewsRepository
import com.semdelion.data.repositories.NewsRepository
import com.semdelion.domain.usecases.news.SaveNews
import com.semdelion.presentation.navigation.NewsNavigationArg
import com.semdelion.presentation.viewmodels.NewsDetailsViewModel

class NewsDetailsViewModelFactory(private val navArg: NewsNavigationArg) :
    ViewModelProvider.Factory {

    private val favoriteNews by lazy {
        FavoriteNewsRepository()
    }

    private val saveNews by lazy {
        SaveNews(favoriteNews = favoriteNews)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsDetailsViewModel::class.java)) {
            return NewsDetailsViewModel(navArg, saveNews) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}