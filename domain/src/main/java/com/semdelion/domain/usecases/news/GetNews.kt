package com.semdelion.domain.usecases.news

import com.semdelion.domain.models.News
import com.semdelion.domain.repositories.INewsRepository

class GetNews(private val newsRepository: INewsRepository) {
    fun get(): List<News> {
        return newsRepository.getNews()
    }
}