package com.semdelion.domain.models

data class NewsModel(
    val title: String,
    val link: String,
    val creator: List<String>,
    val videoURL: String,
    val description: String,
    val content: String,
    val pubDate: String,
    val imageURL: String
)
