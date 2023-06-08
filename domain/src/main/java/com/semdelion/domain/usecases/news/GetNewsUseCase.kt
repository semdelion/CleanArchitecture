package com.semdelion.domain.usecases.news

import com.semdelion.domain.models.NewsModel
import com.semdelion.domain.repositories.INewsRepository

class GetNewsUseCase(private val newsRepository: INewsRepository) {
    fun get(): List<NewsModel> {
        return newsRepository.getNews()
    }
}