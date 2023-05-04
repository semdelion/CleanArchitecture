package com.semdelion.domain.repositories

import com.semdelion.domain.models.NewsModel

interface IFavoriteNewsRepository {
    fun addFavoriteNews(news: NewsModel): Boolean

    fun getFavoriteNews(): List<NewsModel>

    fun deleteFavoriteNews(key: NewsModel): Boolean
}