package com.semdelion.domain.usecases.news

import com.semdelion.domain.models.News
import com.semdelion.domain.repositories.IFavoriteNewsRepository

class DeleteNews(private val favoriteNews: IFavoriteNewsRepository) {

    fun deleteFavoriteNews(newsModel: News): Boolean {
        return false
    }
}