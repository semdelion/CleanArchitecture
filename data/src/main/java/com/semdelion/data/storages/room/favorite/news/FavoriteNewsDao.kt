package com.semdelion.data.storages.room.favorite.news

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteNewsDao {
    @Query("SELECT * from favorite_news_table")
    fun getFavoriteNews(): List<FavoriteNewsEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoriteNews: FavoriteNewsEntity)

    @Query("DELETE FROM favorite_news_table WHERE hashcode = :id")
    fun deleteFavoriteNewsById(id: String)
}