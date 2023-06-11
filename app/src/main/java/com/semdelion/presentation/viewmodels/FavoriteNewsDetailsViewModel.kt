package com.semdelion.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semdelion.domain.models.NewsModel
import com.semdelion.domain.usecases.news.DeleteNewsUseCase
import com.semdelion.domain.usecases.news.SaveNewsUseCase
import com.semdelion.presentation.navigation.NewsNavigationArg
import com.semdelion.presentation.navigation.toNewsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class FavoriteNewsDetailsViewModel(
    private val newsNavigationArg: NewsNavigationArg,
    private val deleteNewsUseCase: DeleteNewsUseCase
) : ViewModel() {

    val imageUrl: String = newsNavigationArg.imageURL

    private val _titleLive = MutableLiveData(newsNavigationArg.title)
    val titleLive: LiveData<String> = _titleLive

    private val _contentLive = MutableLiveData(newsNavigationArg.content)
    val contentLive: LiveData<String> = _contentLive

    private val _dateLive = MutableLiveData(newsNavigationArg.pubDate)
    val dateLive: LiveData<String> = _dateLive

    val creators: List<String> = newsNavigationArg.creator

    val link: String = newsNavigationArg.link

    private val _deleteNewsState = MutableSharedFlow<String>()
    val deleteNewsState = _deleteNewsState.asSharedFlow()

    fun deleteFavoriteNews() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = deleteNewsUseCase.delete(newsNavigationArg.toNewsModel())

            _deleteNewsState.emit(if (result) "Successful delete!" else "Failure delete!")
        }
    }
}