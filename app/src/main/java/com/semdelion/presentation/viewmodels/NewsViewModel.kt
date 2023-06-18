package com.semdelion.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.semdelion.domain.models.NewsModel
import com.semdelion.domain.usecases.news.GetNewsUseCase
import com.semdelion.presentation.viewmodels.base.BaseListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewsViewModel(private val getNewsUseCase: GetNewsUseCase) : BaseListViewModel() {
    private val _items = MutableLiveData<MutableList<NewsModel>>()
    val items: LiveData<MutableList<NewsModel>> = _items

    private var _nextPageId: String? = null

    init {
        loadNews(_nextPageId)
    }

    fun loadNextPage() : Job {
        return loadNews(_nextPageId)
    }

    fun refreshNews() : Job {
        _nextPageId = null
        return viewModelScope.launch(Dispatchers.IO) {
            getItemsWithState{
                val newsPageModel = getNewsUseCase.get(null)
                _nextPageId = newsPageModel.nextPage
                _items.postValue(newsPageModel.News.toMutableList())
            }
        }
    }

    private fun loadNews(page:String?): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            getItemsWithState {
                val itemsData = _items.value ?: mutableListOf()
                val newsPageModel = getNewsUseCase.get(page)
                _nextPageId = newsPageModel.nextPage
                itemsData.addAll(newsPageModel.News)
                _items.postValue(itemsData)
            }
        }
    }
}