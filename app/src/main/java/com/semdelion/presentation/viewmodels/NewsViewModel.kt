package com.semdelion.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.semdelion.domain.models.NewsModel
import com.semdelion.domain.usecases.news.GetNewsUseCase
import com.semdelion.presentation.viewmodels.base.BaseListViewModel
import com.semdelion.presentation.viewmodels.extentions.ListViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsViewModel(private val getNewsUseCase: GetNewsUseCase) : BaseListViewModel() {
    private val _items = MutableLiveData<MutableList<NewsModel>>()
    val items: LiveData<MutableList<NewsModel>> = _items

    init {
        loadNews()
    }

    fun loadNews(): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            try {
                _viewState.emit(ListViewState.Loading)

                val news = getNewsUseCase.get()
                _items.postValue(news.toMutableList())

                _viewState.emit(ListViewState.Success)
            } catch (ex: Exception) {
                _viewState.emit(ListViewState.Error(ex))
            }
        }
    }
}