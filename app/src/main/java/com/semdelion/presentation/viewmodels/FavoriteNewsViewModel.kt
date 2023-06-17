package com.semdelion.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.semdelion.domain.models.NewsModel
import com.semdelion.domain.usecases.news.GetFavoriteNewsUseCase
import com.semdelion.presentation.viewmodels.base.BaseListViewModel
import com.semdelion.presentation.viewmodels.extentions.ListViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FavoriteNewsViewModel(private val getFavoriteNewsUseCase: GetFavoriteNewsUseCase) :
    BaseListViewModel() {
    private val _items = MutableLiveData<MutableList<NewsModel>>()
    val items: LiveData<MutableList<NewsModel>> = _items

    fun loadFavoriteNews(): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            try {
                _viewState.emit(ListViewState.Loading)

                val news = getFavoriteNewsUseCase.getFavoriteNews()
                _items.postValue(news.toMutableList())

                _viewState.emit(ListViewState.Success)
            } catch (ex: Exception) {
                _viewState.emit(ListViewState.Error(ex))
            }
        }
    }
}