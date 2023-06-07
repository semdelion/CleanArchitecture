package com.semdelion.data.repositories

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.semdelion.data.storages.IFavoriteNewsStorage
import com.semdelion.data.storages.room.favorite.news.FavoriteNewsEntity
import com.semdelion.domain.models.News
import com.semdelion.domain.repositories.IFavoriteNewsRepository

class FavoriteNewsRepository(private val favoriteNewsStorage: IFavoriteNewsStorage) :
    IFavoriteNewsRepository {
    override suspend fun addFavoriteNews(news: News): Boolean {

        val newsEntity = FavoriteNewsEntity(
            id = news.hashCode(),
            title = news.title,
            link = news.link,
            //creator = news.creator,
            content = news.content,
            pubDate = news.pubDate,
            imageURL = news.imageURL,
        )
        val result = favoriteNewsStorage.addNews(newsEntity)
        return result;
    }

    override suspend fun getFavoriteNews(): List<News> {
        val result = favoriteNewsStorage.getNews()

        if (result.isEmpty()) {
            return listOf()
        } else {
            val list = mutableListOf<News>()
            for (item in result) {
                list.add(
                    News(
                        title = item.title,
                        link = item.link,
                        //TODO
                        creator = listOf(),
                        videoURL = "",
                        description = "",
                        content = item.content,
                        pubDate = item.pubDate,
                        imageURL = item.imageURL
                    )
                )
            }
            return list
        }

    }

    override fun deleteFavoriteNews(key: News): Boolean {
        TODO("Not yet implemented")
    }
}