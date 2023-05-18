package com.semdelion.data.services.interfaces

import com.semdelion.data.services.models.NewsModel
import retrofit2.Call
import retrofit2.http.GET

interface NewsServices {
    @GET("1/news")
    fun getNews(): Call<List<NewsModel>>
}