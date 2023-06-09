package com.semdelion.data.storages

import com.semdelion.data.storages.interfaces.IFavoriteNewsStorage
import com.semdelion.data.storages.room.FavoriteNewsDao
import com.semdelion.data.storages.room.FavoriteNewsEntity
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

    override suspend fun deleteNews(newsId: Int): Boolean {
        return withContext(Dispatchers.IO) {
            val result = favoriteNewsDao.deleteById(newsId)
            return@withContext result>0
        }
    }
}