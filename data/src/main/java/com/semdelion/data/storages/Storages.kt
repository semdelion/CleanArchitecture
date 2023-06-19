package com.semdelion.data.storages

import android.content.Context
import androidx.room.Room
import com.semdelion.data.storages.interfaces.IFavoriteNewsStorage
import com.semdelion.data.storages.room.AppDatabase

object Storages {
    private lateinit var applicationContext: Context

    private val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db").build()
    }

    val favoriteNewsStorage by lazy<IFavoriteNewsStorage> {
        RoomFavoriteNewsStorage(appDatabase.getFavoriteNewsDao())
    }

    fun init(context:Context) {
        applicationContext = context
    }
}