package com.semdelion.presentation.views.factories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.semdelion.data.repositories.FavoriteNewsRepository
import com.semdelion.data.repositories.NewsRepository
import com.semdelion.data.storages.RoomFavoriteNewsStorage
import com.semdelion.data.storages.room.AppDatabase
import com.semdelion.domain.usecases.news.GetFavoriteNews
import com.semdelion.domain.usecases.news.SaveNews
import com.semdelion.presentation.navigation.NewsNavigationArg
import com.semdelion.presentation.viewmodels.NewsDetailsViewModel

class NewsDetailsViewModelFactory(private val navArg: NewsNavigationArg, context: Context) :
    ViewModelProvider.Factory {

    private val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, "database.db").build()
    }

    private val favoriteNews by lazy {
        FavoriteNewsRepository(RoomFavoriteNewsStorage(appDatabase.getFavoriteNewsDao()))
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