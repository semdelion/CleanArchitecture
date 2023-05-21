package com.semdelion.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semdelion.domain.models.News
import com.semdelion.domain.repositories.INewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: INewsRepository) : ViewModel() {
    private val _newsItems = MutableLiveData<MutableList<News>>()
    val newsItems: LiveData<MutableList<News>> = _newsItems
    init {
        val list = mutableListOf<News>()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val news = newsRepository.getNews()
                val news2 = news
            }
            catch (ex: Exception) {
                val exm = ex.message
            }

        }
        viewModelScope.launch {
            delay(2000)
            list.add(
                News(
                    title = "test1",
                    link = "fdfd",
                    creator = listOf("ivan", "sergei"),
                    videoURL = "",
                    description = "dfdfddddddjjjfjfjfjfjjf",
                    content = "ccd",
                    pubDate = "23.10.2023",
                    imageURL = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/440px-Image_created_with_a_mobile_phone.png"
                )
            )
            list.add(
                News(
                    title = "test2",
                    link = "fdfd",
                    creator = listOf("ivan", "sergei"),
                    videoURL = "",
                    description = "dfdfddddddjjjfjfjfjfjjf",
                    content = "ccd",
                    pubDate = "23.10.2023",
                    imageURL = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/440px-Image_created_with_a_mobile_phone.png"
                )
            )
            list.add(
                News(
                    title = "test2",
                    link = "fdfd",
                    creator = listOf("ivan", "sergei"),
                    videoURL = "",
                    description = "dfdfddddddjjjfjfjfjfjjf",
                    content = "ccd",
                    pubDate = "23.10.2023",
                    imageURL = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/440px-Image_created_with_a_mobile_phone.png"
                )
            )
            list.add(
                News(
                    title = "test3",
                    link = "fdfd",
                    creator = listOf("ivan", "sergei"),
                    videoURL = "",
                    description = "dfdfddddddjjjfjfjfjfjjf",
                    content = "ccd",
                    pubDate = "23.10.2023",
                    imageURL = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/440px-Image_created_with_a_mobile_phone.png"
                )
            )
            list.add(
                News(
                    title = "test4",
                    link = "fdfd",
                    creator = listOf("ivan", "sergei"),
                    videoURL = "",
                    description = "dfdfddddddjjjfjfjfjfjjf",
                    content = "ccd",
                    pubDate = "23.10.2023",
                    imageURL = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/440px-Image_created_with_a_mobile_phone.png"
                )
            )
            list.add(
                News(
                    title = "test5",
                    link = "fdfd",
                    creator = listOf("ivan", "sergei"),
                    videoURL = "",
                    description = "dfdfddddddjjjfjfjfjfjjf",
                    content = "ccd",
                    pubDate = "23.10.2023",
                    imageURL = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/440px-Image_created_with_a_mobile_phone.png"
                )
            )
            _newsItems.value = (list)
        }
    }
}