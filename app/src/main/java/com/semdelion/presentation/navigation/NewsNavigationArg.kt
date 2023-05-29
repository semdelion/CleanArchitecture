package com.semdelion.presentation.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsNavigationArg(
    val title: String,
    val link: String,
    val creator: List<String>,
    val description: String,
    val content: String,
    val pubDate: String,
    val imageURL: String) : Parcelable