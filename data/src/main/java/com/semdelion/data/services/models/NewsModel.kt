package com.semdelion.data.services.models

import com.google.gson.annotations.SerializedName

data class NewsModel (
    val title: String,
    val link: String,
    val keywords: List<String>,
    val creator: List<String>,

    @SerializedName("video_url")
    val videoURL: String? = null,

    val description: String,
    val content: String,
    val pubDate: String,

    @SerializedName("image_url")
    val imageURL: String? = null,

    @SerializedName("source_id")
    val sourceID: String,

    val category: List<String>,
    val country: List<String>,
    val language: String
)