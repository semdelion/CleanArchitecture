package com.semdelion.domain.usecases.news

import com.semdelion.domain.models.NewsModel
import com.semdelion.domain.repositories.INewsRepository

class GetNewsApi(private val news: INewsRepository) {

    fun getNews(): List<NewsModel> {
        //TODO
        return listOf<NewsModel>()
    }
}