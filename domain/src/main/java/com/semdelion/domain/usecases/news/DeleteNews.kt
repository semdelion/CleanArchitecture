package com.semdelion.domain.usecases.news

import com.semdelion.domain.models.News
import com.semdelion.domain.repositories.IFavoriteNewsRepository

class DeleteNews(private val favoriteNews: IFavoriteNewsRepository) {
    suspend fun delete(news: News): Boolean {
        return favoriteNews.deleteFavoriteNews(news)
    }
}