package com.semdelion.domain.usecases.news

import com.semdelion.domain.models.News
import com.semdelion.domain.repositories.IFavoriteNewsRepository

class GetFavoriteNews(private val favoriteNews: IFavoriteNewsRepository) {
    suspend fun getFavoriteNews(): List<News> {
        val result = favoriteNews.getFavoriteNews()
        return result
    }
}