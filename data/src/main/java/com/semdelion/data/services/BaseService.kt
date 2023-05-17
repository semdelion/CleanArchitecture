package com.semdelion.data.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseService {

    private val apiKey = "pub_21221020bb9c580281896a2305077b7ffbe0f"

    companion object {
         private var host = "https://newsdata.io/api/"
         val retrofit = Retrofit.Builder()
                .baseUrl(host)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}