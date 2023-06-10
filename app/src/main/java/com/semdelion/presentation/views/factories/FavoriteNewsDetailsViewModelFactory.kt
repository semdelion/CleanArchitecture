package com.semdelion.presentation.views.factories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.semdelion.data.repositories.FavoriteNewsRepositoryImpl
import com.semdelion.data.storages.RoomFavoriteNewsStorage
import com.semdelion.data.storages.room.AppDatabase
import com.semdelion.domain.usecases.news.DeleteNewsUseCase
import com.semdelion.domain.usecases.news.SaveNewsUseCase
import com.semdelion.presentation.navigation.NewsNavigationArg
import com.semdelion.presentation.viewmodels.FavoriteNewsDetailsViewModel
import com.semdelion.presentation.viewmodels.NewsDetailsViewModel

class FavoriteNewsDetailsViewModelFactory(private val navArg: NewsNavigationArg, context: Context) :
    ViewModelProvider.Factory {

    private val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, "database.db").build()
    }

    private val favoriteNews by lazy {
        FavoriteNewsRepositoryImpl(RoomFavoriteNewsStorage(appDatabase.getFavoriteNewsDao()))
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