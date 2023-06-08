package com.semdelion.domain.repositories

import com.semdelion.domain.models.NewsModel

interface IFavoriteNewsRepository {
    suspend fun addFavoriteNews(newsModel: NewsModel): Boolean

    suspend fun getFavoriteNews(): List<NewsModel>

    suspend fun deleteFavoriteNews(key: NewsModel): Boolean
}