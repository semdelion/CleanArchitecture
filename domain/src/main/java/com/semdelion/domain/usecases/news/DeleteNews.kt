package com.semdelion.domain.usecases.news

import com.semdelion.domain.models.NewsModel
import com.semdelion.domain.repositories.IFavoriteNewsRepository

class DeleteNews(private val favoriteNews: IFavoriteNewsRepository) {

    fun deleteFavoriteNews(newsModel: NewsModel): Boolean {
        return false
    }
}