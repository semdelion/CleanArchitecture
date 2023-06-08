package com.semdelion.domain.usecases.news

import com.semdelion.domain.models.News
import com.semdelion.domain.repositories.IFavoriteNewsRepository

class SaveNews(private val favoriteNews: IFavoriteNewsRepository) {
    suspend fun save(news: News): Boolean {
        return favoriteNews.addFavoriteNews(news)
    }
}