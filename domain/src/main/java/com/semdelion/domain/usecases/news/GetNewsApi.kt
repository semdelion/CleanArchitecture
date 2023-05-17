package com.semdelion.domain.usecases.news

import com.semdelion.domain.models.News
import com.semdelion.domain.repositories.INewsRepository

class GetNewsApi(private val news: INewsRepository) {

    fun getNews(): List<News> {
        return news.getNews()
    }
}