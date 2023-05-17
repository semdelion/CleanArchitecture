package com.semdelion.domain.repositories

import com.semdelion.domain.models.News

interface IFavoriteNewsRepository {
    fun addFavoriteNews(news: News): Boolean

    fun getFavoriteNews(): List<News>

    fun deleteFavoriteNews(key: News): Boolean
}