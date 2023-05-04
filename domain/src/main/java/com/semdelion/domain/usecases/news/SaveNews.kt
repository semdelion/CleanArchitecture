package com.semdelion.domain.usecases.news

import com.semdelion.domain.repositories.IFavoriteNewsRepository

class SaveNews(private val favoriteNews: IFavoriteNewsRepository) {

    fun saveNews():Boolean {
        //TODO
        return false
    }
}