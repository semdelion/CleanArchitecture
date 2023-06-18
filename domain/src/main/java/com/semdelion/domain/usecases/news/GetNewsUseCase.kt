package com.semdelion.domain.usecases.news

import com.semdelion.domain.models.NewsPageModel
import com.semdelion.domain.repositories.INewsRepository

class GetNewsUseCase(private val newsRepository: INewsRepository) {
    fun get(page:String? = null): NewsPageModel {
        return newsRepository.getNews(page)
    }
}
