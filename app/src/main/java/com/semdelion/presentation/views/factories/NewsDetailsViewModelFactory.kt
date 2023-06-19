package com.semdelion.presentation.views.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.semdelion.data.repositories.FavoriteNewsRepositoryImpl
import com.semdelion.data.storages.Storages
import com.semdelion.data.storages.RoomFavoriteNewsStorage
import com.semdelion.domain.usecases.news.SaveNewsUseCase
import com.semdelion.presentation.navigation.NewsNavigationArg
import com.semdelion.presentation.viewmodels.NewsDetailsViewModel

class NewsDetailsViewModelFactory(private val navArg: NewsNavigationArg) :
    ViewModelProvider.Factory {

    private val favoriteNews by lazy {
        FavoriteNewsRepositoryImpl(Storages.favoriteNewsStorage)
    }

    private val saveNewsUseCase by lazy {
        SaveNewsUseCase(favoriteNews = favoriteNews)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsDetailsViewModel::class.java)) {
            return NewsDetailsViewModel(navArg, saveNewsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}