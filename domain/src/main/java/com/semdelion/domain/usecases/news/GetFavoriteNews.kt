package com.semdelion.domain.usecases.news

import com.semdelion.domain.models.News
import com.semdelion.domain.repositories.IFavoriteNewsRepository

class GetFavoriteNews(private val favoriteNews: IFavoriteNewsRepository) {
    fun getFavoriteNews(): List<News> {
        //TODO
        return listOf<News>()
    }
}