package com.semdelion.presentation.navigation

import android.os.Parcelable
import com.semdelion.domain.models.NewsModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsNavigationArg(
    val title: String,
    val link: String,
    val creator: List<String>,
    val content: String,
    val pubDate: String,
    val imageURL: String
) : Parcelable

fun NewsNavigationArg.toNewsModel(): NewsModel {
    return NewsModel(
        title = this.title,
        link = this.link,
        creator = this.creator,
        videoURL = "",
        description = "",
        content = this.content,
        pubDate = this.pubDate,
        imageURL = this.imageURL
    )
}