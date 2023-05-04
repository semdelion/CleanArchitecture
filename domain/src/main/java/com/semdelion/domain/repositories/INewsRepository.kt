package com.semdelion.domain.repositories

import com.semdelion.domain.models.NewsModel

interface INewsRepository {
    fun getNews(): List<NewsModel>
}