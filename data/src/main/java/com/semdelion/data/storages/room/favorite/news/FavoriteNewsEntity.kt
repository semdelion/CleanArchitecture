package com.semdelion.data.storages.room.favorite.news

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.semdelion.data.storages.room.converters.ListTypeConverter

@Entity(tableName = "favorite_news_table")
@TypeConverters(ListTypeConverter::class)
data class FavoriteNewsEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "link") val link: String,
    @ColumnInfo(name = "creator") val creator: List<String>,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "pubDate") val pubDate: String,
    @ColumnInfo(name = "imageURL") val imageURL: String
)