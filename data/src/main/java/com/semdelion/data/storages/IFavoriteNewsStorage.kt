package com.semdelion.data.storages

import com.semdelion.data.storages.room.favorite.news.FavoriteNewsEntity

interface IFavoriteNewsStorage {
    suspend fun getNews(): List<FavoriteNewsEntity>
    suspend fun addNews(news: FavoriteNewsEntity) : Boolean
    suspend fun deleteNews(newsId: Int) : Boolean
}