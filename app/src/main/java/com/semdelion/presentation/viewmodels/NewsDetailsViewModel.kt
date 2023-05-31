package com.semdelion.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semdelion.presentation.navigation.NewsNavigationArg

class NewsDetailsViewModel(private val newsNavigationArg: NewsNavigationArg) : ViewModel() {

    val imageUrl: String = newsNavigationArg.imageURL

    private val _titleLive = MutableLiveData<String>(newsNavigationArg.title)
    val titleLive: LiveData<String> = _titleLive
}