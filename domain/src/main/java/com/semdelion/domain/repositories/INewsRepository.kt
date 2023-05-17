package com.semdelion.domain.repositories

import com.semdelion.domain.models.News

interface INewsRepository {
    fun getNews(): List<News>
}