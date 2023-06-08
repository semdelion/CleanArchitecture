package com.semdelion.data.services.models

data class NewsResult(
    val status: String,
    val results: List<NewsDataModel>
)