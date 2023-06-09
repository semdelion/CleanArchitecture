package com.semdelion.data.storages.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        FavoriteNewsEntity::class
    ]
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getFavoriteNewsDao(): FavoriteNewsDao
}