package com.semdelion.data.services.models

import com.google.gson.annotations.SerializedName
import com.semdelion.domain.models.NewsModel

data class NewsDataModel(
    val title: String,
    val link: String,
    val keywords: List<String>,
    val creator: List<String>?,

    @SerializedName("video_url")
    val videoURL: String? = null,

    val description: String? = null,
    val content: String? = null,
    val pubDate: String,

    @SerializedName("image_url")
    val imageURL: String? = null,

    @SerializedName("source_id")
    val sourceID: String,

    val category: List<String>,
    val country: List<String>,
    val language: String
)

fun NewsDataModel.toNewsModel() : NewsModel {
    return NewsModel(
        title = this.title,
        link = this.link,
        creator = this.creator ?: listOf(),
        videoURL = this.videoURL ?: "",
        description = this.description ?: "",
        content = this.content ?: "",
        pubDate = this.pubDate,
        imageURL = this.imageURL ?: "",
    )
}