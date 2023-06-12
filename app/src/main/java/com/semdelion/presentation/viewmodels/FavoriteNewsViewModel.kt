package com.semdelion.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semdelion.domain.models.NewsModel
import com.semdelion.domain.usecases.news.GetFavoriteNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteNewsViewModel(private val getFavoriteNewsUseCase: GetFavoriteNewsUseCase) : ViewModel() {
    private val _newsModelItems = MutableLiveData<MutableList<NewsModel>>()
    val newsModelItems: LiveData<MutableList<NewsModel>> = _newsModelItems

    init {
        loadFavoriteNews()
    }

    fun loadFavoriteNews() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val news = getFavoriteNewsUseCase.getFavoriteNews()
                _newsModelItems.postValue(news.toMutableList())
            } catch (ex: Exception) {
                val exm = ex.message
            }
        }
    }
}