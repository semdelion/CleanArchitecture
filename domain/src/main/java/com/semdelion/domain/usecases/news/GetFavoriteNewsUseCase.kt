package com.semdelion.domain.usecases.news

import com.semdelion.domain.models.NewsModel
import com.semdelion.domain.repositories.IFavoriteNewsRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteNewsUseCase(private val favoriteNews: IFavoriteNewsRepository) {
    fun getFavoriteNews(): Flow<List<NewsModel>> {
        return favoriteNews.getFavoriteNews()
    }
}