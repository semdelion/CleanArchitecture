package com.semdelion.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semdelion.domain.models.NewsModel
import com.semdelion.domain.usecases.news.GetNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(private val getNewsUseCase: GetNewsUseCase) : ViewModel() {
    private val _newsModelItems = MutableLiveData<MutableList<NewsModel>>()
    val newsModelItems: LiveData<MutableList<NewsModel>> = _newsModelItems

    init {
        loadNews()
    }

    fun loadNews() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val news = getNewsUseCase.get()
                _newsModelItems.postValue(news.toMutableList())
            } catch (ex: Exception) {
                val exm = ex.message
            }
        }
    }
}