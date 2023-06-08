package com.semdelion.data.repositories

import com.semdelion.data.services.BaseService
import com.semdelion.data.services.interfaces.INewsServices
import com.semdelion.data.services.models.toNewsModel
import com.semdelion.domain.models.NewsModel
import com.semdelion.domain.repositories.INewsRepository

class NewsRepositoryImpl() : BaseService(), INewsRepository {

    private val INewsServices = retrofit.create(INewsServices::class.java)

    override fun getNews(): List<NewsModel> {

        val response = INewsServices.getNews().execute()

        val newsModel = response.body()?.results ?: mutableListOf()

        val newsModels: MutableList<NewsModel> = mutableListOf()
        newsModel.forEach { newsModels.add(it.toNewsModel()) }
        return newsModels
    }
}