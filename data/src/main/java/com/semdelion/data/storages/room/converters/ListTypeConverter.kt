package com.semdelion.data.storages.room.converters

import androidx.room.TypeConverters
import com.google.gson.Gson
import com.semdelion.data.extensions.fromJson

class ListTypeConverter {
    @TypeConverters
    fun fromString(value: String): List<String> {
        return try {
            Gson().fromJson<List<String>>(value)
        } catch (e: Exception) {
            listOf()
        }
    }

    @TypeConverters
    fun fromList(list: List<String>): String {
        return Gson().toJson(list)

    }
}