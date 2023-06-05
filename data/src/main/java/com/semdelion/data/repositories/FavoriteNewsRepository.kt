package com.semdelion.data.repositories

import com.semdelion.domain.models.News
import com.semdelion.domain.repositories.IFavoriteNewsRepository

class FavoriteNewsRepository(): IFavoriteNewsRepository {
    override fun addFavoriteNews(news: News): Boolean {
        TODO("Not yet implemented")
    }

    override fun getFavoriteNews(): List<News> {
        TODO("Not yet implemented")
    }

    override fun deleteFavoriteNews(key: News): Boolean {
        TODO("Not yet implemented")
    }
}