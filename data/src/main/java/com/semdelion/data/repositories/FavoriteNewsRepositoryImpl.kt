package com.semdelion.data.repositories

import com.semdelion.data.storages.interfaces.IFavoriteNewsStorage
import com.semdelion.data.storages.room.FavoriteNewsEntity
import com.semdelion.domain.models.NewsModel
import com.semdelion.domain.repositories.IFavoriteNewsRepository

class FavoriteNewsRepositoryImpl(private val favoriteNewsStorage: IFavoriteNewsStorage) :
    IFavoriteNewsRepository {
    override suspend fun addFavoriteNews(newsModel: NewsModel): Boolean {

        val newsEntity = FavoriteNewsEntity(
            id = newsModel.hashCode(),
            title = newsModel.title,
            link = newsModel.link,
            creator = newsModel.creator,
            content = newsModel.content,
            pubDate = newsModel.pubDate,
            imageURL = newsModel.imageURL,
        )

        return favoriteNewsStorage.addNews(newsEntity)
    }

    override suspend fun getFavoriteNews(): List<NewsModel> {
        val result = favoriteNewsStorage.getNews()

        return if (result.isEmpty()) {
            listOf()
        } else {
            val list = mutableListOf<NewsModel>()
            result.forEach { list.add(it.toFavoriteNewsModel())}
            list
        }
    }

    override suspend fun deleteFavoriteNews(key: NewsModel): Boolean {
        return favoriteNewsStorage.deleteNews(key.hashCode())
    }
}