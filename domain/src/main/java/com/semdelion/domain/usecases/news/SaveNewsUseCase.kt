package com.semdelion.domain.usecases.news

import com.semdelion.domain.models.NewsModel
import com.semdelion.domain.repositories.IFavoriteNewsRepository

class SaveNewsUseCase(private val favoriteNews: IFavoriteNewsRepository) {
    suspend fun save(newsModel: NewsModel): Boolean {
        return favoriteNews.addFavoriteNews(newsModel)
    }
}