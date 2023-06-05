package com.semdelion.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semdelion.domain.models.News
import com.semdelion.domain.usecases.news.GetNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(private val getNews: GetNews) : ViewModel() {
    private val _newsItems = MutableLiveData<MutableList<News>>()
    val newsItems: LiveData<MutableList<News>> = _newsItems

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val news = getNews.getNews()
                _newsItems.postValue(news.toMutableList())
            } catch (ex: Exception) {
                val exm = ex.message
            }
        }
    }
}