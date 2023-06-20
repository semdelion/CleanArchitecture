package com.semdelion.domain.repositories

import com.semdelion.domain.models.NewsModel
import kotlinx.coroutines.flow.Flow

interface IFavoriteNewsRepository {
    suspend fun addFavoriteNews(newsModel: NewsModel): Boolean

    fun getFavoriteNews(): Flow<List<NewsModel>>

    suspend fun deleteFavoriteNews(key: NewsModel): Boolean
}