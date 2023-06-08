package com.semdelion.presentation.views.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.semdelion.data.repositories.NewsRepositoryImpl
import com.semdelion.domain.usecases.news.GetNewsUseCase
import com.semdelion.presentation.viewmodels.NewsViewModel

class NewsViewModelFactory() : ViewModelProvider.Factory {

    private val newsRepositoryImpl by lazy {
        NewsRepositoryImpl()
    }

    private val getUserUseCase by lazy {
        GetNewsUseCase(newsRepository = newsRepositoryImpl)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(getUserUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}