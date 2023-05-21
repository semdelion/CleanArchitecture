package com.semdelion.data.services.interfaces

import com.semdelion.data.services.models.NewsResult
import retrofit2.Call
import retrofit2.http.GET

interface NewsServices {
    @GET("1/news?apikey=pub_21221020bb9c580281896a2305077b7ffbe0f&language=ru")
    fun getNews(): Call<NewsResult>
}