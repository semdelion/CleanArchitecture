package com.semdelion.domain.repositories

import com.semdelion.domain.models.News

interface IFavoriteNewsRepository {
    suspend fun addFavoriteNews(news: News): Boolean

    suspend fun getFavoriteNews(): List<News>

    fun deleteFavoriteNews(key: News): Boolean
}