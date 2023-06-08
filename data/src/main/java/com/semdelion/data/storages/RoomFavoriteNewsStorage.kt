package com.semdelion.data.storages

import com.semdelion.data.storages.room.favorite.news.FavoriteNewsDao
import com.semdelion.data.storages.room.favorite.news.FavoriteNewsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomFavoriteNewsStorage(private val favoriteNewsDao: FavoriteNewsDao): IFavoriteNewsStorage {
    override suspend fun getNews(): List<FavoriteNewsEntity> {
        return withContext(Dispatchers.IO) {
            return@withContext favoriteNewsDao.getFavoriteNews()
        }
    }

    override suspend fun addNews(news: FavoriteNewsEntity): Boolean {
        return withContext(Dispatchers.IO) {
            val result = favoriteNewsDao.insert(news)
            return@withContext result>0
        }
    }

    override suspend fun deleteNews(id: Int): Boolean {
        return withContext(Dispatchers.IO) {
            val result = favoriteNewsDao.deleteById(id)
            return@withContext result>0
        }
    }
}