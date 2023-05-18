package com.semdelion.data.repositories

import com.semdelion.data.services.BaseService
import com.semdelion.data.services.interfaces.NewsServices
import com.semdelion.domain.models.News
import com.semdelion.domain.repositories.INewsRepository

class NewsRepository(): BaseService(), INewsRepository {

    val newsServices = BaseService.retrofit.create(NewsServices::class.java)

    override fun getNews(): List<News> {
        var response = newsServices.getNews()

        var newsModel = response.execute()
        val news:MutableList<News> = mutableListOf()
        newsModel.body()?.forEach { news.add( News(
            title = it.title,
            link = it.link,
            creator = it.category,
            videoURL = it.videoURL ?: "",
            description = it.description,
            content = it.content,
            pubDate = it.pubDate,
            imageURL = it.imageURL ?: "",
        )) }
        return news
    }
}