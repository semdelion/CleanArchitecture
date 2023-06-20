package com.semdelion.data.repositories

import com.semdelion.data.storages.interfaces.IFavoriteNewsStorage
import com.semdelion.data.storages.room.FavoriteNewsEntity
import com.semdelion.domain.models.NewsModel
import com.semdelion.domain.repositories.IFavoriteNewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

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

    override fun getFavoriteNews(): Flow<List<NewsModel>> {
        val result = favoriteNewsStorage.getNews()

        return result.map {
            val list = mutableListOf<NewsModel>()
            if (it.isNotEmpty()) {
                it.forEach {item ->  list.add(item.toFavoriteNewsModel())}
            }
            list
        }
    }

    override suspend fun deleteFavoriteNews(key: NewsModel): Boolean {
        return favoriteNewsStorage.deleteNews(key.hashCode())
    }
}