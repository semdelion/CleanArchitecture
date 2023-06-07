package com.semdelion.data.storages.room.favorite.news

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteNewsDao {
    @Query("SELECT * from favorite_news_table")
    suspend fun getFavoriteNews(): List<FavoriteNewsEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoriteNews: FavoriteNewsEntity): Long

    @Query("DELETE FROM favorite_news_table WHERE id = :id")
    suspend fun deleteById(id: String)
}