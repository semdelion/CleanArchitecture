package com.semdelion.presentation.views.factories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.semdelion.data.repositories.FavoriteNewsRepositoryImpl
import com.semdelion.data.storages.Storages
import com.semdelion.domain.usecases.news.DeleteNewsUseCase
import com.semdelion.presentation.navigation.NewsNavigationArg
import com.semdelion.presentation.viewmodels.FavoriteNewsDetailsViewModel

class FavoriteNewsDetailsViewModelFactory(private val navArg: NewsNavigationArg) :
    ViewModelProvider.Factory {

    private val favoriteNews by lazy {
        FavoriteNewsRepositoryImpl(Storages.favoriteNewsStorage)
    }

    private val deleteNewsUseCase by lazy {
        DeleteNewsUseCase(favoriteNews = favoriteNews)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteNewsDetailsViewModel::class.java)) {
            return FavoriteNewsDetailsViewModel(navArg, deleteNewsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}