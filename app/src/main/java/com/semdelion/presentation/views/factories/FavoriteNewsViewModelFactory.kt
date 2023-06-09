package com.semdelion.presentation.views.factories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.semdelion.data.repositories.FavoriteNewsRepositoryImpl
import com.semdelion.data.repositories.NewsRepositoryImpl
import com.semdelion.data.storages.RoomFavoriteNewsStorage
import com.semdelion.data.storages.room.AppDatabase
import com.semdelion.domain.usecases.news.GetFavoriteNewsUseCase
import com.semdelion.domain.usecases.news.GetNewsUseCase
import com.semdelion.presentation.viewmodels.FavoriteNewsViewModel
import com.semdelion.presentation.viewmodels.NewsViewModel

class FavoriteNewsViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, "database.db").build()
    }

    private val favoriteNews by lazy {
        FavoriteNewsRepositoryImpl(RoomFavoriteNewsStorage(appDatabase.getFavoriteNewsDao()))
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