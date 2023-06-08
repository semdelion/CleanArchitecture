package com.semdelion.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semdelion.domain.models.News
import com.semdelion.domain.usecases.news.SaveNews
import com.semdelion.presentation.navigation.NewsNavigationArg
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class NewsDetailsViewModel(
    private val newsNavigationArg: NewsNavigationArg,
    private val saveNews: SaveNews
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

    private val _saveNewsState = MutableSharedFlow<String>()
    val saveNewsState = _saveNewsState.asSharedFlow()

    public fun addToFavoriteNews() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = saveNews.save(
                News(
                    title = newsNavigationArg.title,
                    link = newsNavigationArg.link,
                    creator = newsNavigationArg.creator,
                    videoURL = "",
                    description = "",
                    content = newsNavigationArg.content,
                    pubDate = newsNavigationArg.pubDate,
                    imageURL = newsNavigationArg.imageURL
                )
            )

            //val result2 = getFavoriteNews.getFavoriteNews()

           // val result4 = result2
        }
    }
}