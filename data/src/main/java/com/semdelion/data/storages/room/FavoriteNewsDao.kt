package com.semdelion.data.storages.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteNewsDao {
    @Query("SELECT * from favorite_news_table")
    fun getFavoriteNews(): Flow<List<FavoriteNewsEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoriteNews: FavoriteNewsEntity): Long

    @Query("DELETE FROM favorite_news_table WHERE id = :id")
    suspend fun deleteById(id: Int): Int
}