package com.semdelion.domain.usecases.news

import com.semdelion.domain.models.NewsModel
import com.semdelion.domain.repositories.IFavoriteNewsRepository

class GetFavoriteNewsUseCase(private val favoriteNews: IFavoriteNewsRepository) {
    suspend fun getFavoriteNews(): List<NewsModel> {
        return favoriteNews.getFavoriteNews()
    }
}