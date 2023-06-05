package com.semdelion.presentation.views.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.semdelion.data.repositories.NewsRepository
import com.semdelion.domain.usecases.GetUser
import com.semdelion.domain.usecases.news.GetNews
import com.semdelion.presentation.viewmodels.NewsViewModel

class NewsViewModelFactory() : ViewModelProvider.Factory {

    private val newsRepository by lazy {
        NewsRepository()
    }

    private val getUser by lazy {
        GetNews(newsRepository = newsRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(getUser) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}