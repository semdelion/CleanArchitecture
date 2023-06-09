package com.semdelion.data.storages.interfaces

import com.semdelion.data.storages.room.FavoriteNewsEntity

interface IFavoriteNewsStorage {
    suspend fun getNews(): List<FavoriteNewsEntity>
    suspend fun addNews(news: FavoriteNewsEntity) : Boolean
    suspend fun deleteNews(newsId: Int) : Boolean
}