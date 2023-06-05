package com.semdelion.data.storages.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_news")
internal class FavoriteNewsTable(
    @PrimaryKey @ColumnInfo(name = "hashCode") val hashCode: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "link") val link: String,
    @ColumnInfo(name = "creator") val creator: ArrayList<String>,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "pubDate") val pubDate: String,
    @ColumnInfo(name = "imageURL") val imageURL: String
)