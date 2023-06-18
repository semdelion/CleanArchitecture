package com.semdelion.domain.repositories
import com.semdelion.domain.models.NewsPageModel

interface INewsRepository {
    fun getNews(page: String?): NewsPageModel
}