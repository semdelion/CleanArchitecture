package com.semdelion.domain.models

data class NewsPageModel(
    val News: List<NewsModel>,
    val nextPage: String?)