package com.semdelion.presentation.views.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.semdelion.data.repositories.FavoriteNewsRepositoryImpl
import com.semdelion.data.storages.Storages
import com.semdelion.domain.usecases.news.GetFavoriteNewsUseCase
import com.semdelion.presentation.viewmodels.FavoriteNewsViewModel

class FavoriteNewsViewModelFactory : ViewModelProvider.Factory {

    private val favoriteNews by lazy {
        FavoriteNewsRepositoryImpl(Storages.favoriteNewsStorage)
    }
    private val getFavoriteNewsUseCase by lazy {
        GetFavoriteNewsUseCase(favoriteNews)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteNewsViewModel::class.java)) {
            return FavoriteNewsViewModel(getFavoriteNewsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}