package com.semdelion.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.semdelion.domain.usecases.news.SaveNewsUseCase
import com.semdelion.presentation.navigation.NewsNavigationArg
import com.semdelion.presentation.navigation.toNewsModel
import com.semdelion.presentation.viewmodels.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class NewsDetailsViewModel(
    private val newsNavigationArg: NewsNavigationArg,
    private val saveNewsUseCase: SaveNewsUseCase
) : BaseViewModel() {

    val imageUrl: String = newsNavigationArg.imageURL

    private val _titleLive = MutableLiveData(newsNavigationArg.title)
    val titleLive: LiveData<String> = _titleLive

    private val _contentLive = MutableLiveData(newsNavigationArg.content)
    val contentLive: LiveData<String> = _contentLive

    private val _dateLive = MutableLiveData(newsNavigationArg.pubDate)
    val dateLive: LiveData<String> = _dateLive

    val creators: List<String> = newsNavigationArg.creator

    val link: String = newsNavigationArg.link

    private val _saveNewsState = MutableSharedFlow<String>()
    val saveNewsState = _saveNewsState.asSharedFlow()

    fun addToFavoriteNews() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = saveNewsUseCase.save(newsNavigationArg.toNewsModel())

            _saveNewsState.emit(if (result) "Successful save!" else "Failure save!")
        }
    }
}