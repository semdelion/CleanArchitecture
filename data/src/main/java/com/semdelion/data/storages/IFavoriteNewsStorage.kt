package com.semdelion.data.storages

import java.util.Objects

interface IFavoriteNewsStorage {
    fun getNews(): List<Objects>
    fun addNews(objects: Objects) : Boolean
    fun deleteNews(objects: Objects) : Boolean
}