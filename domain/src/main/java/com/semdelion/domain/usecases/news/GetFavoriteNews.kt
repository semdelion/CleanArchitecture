package com.semdelion.domain.usecases.news

import com.semdelion.domain.models.NewsModel
import com.semdelion.domain.repositories.IFavoriteNewsRepository

class GetFavoriteNews(private val favoriteNews: IFavoriteNewsRepository) {
    fun getFavoriteNews(): List<NewsModel> {
        //TODO
        return listOf<NewsModel>()
    }
}