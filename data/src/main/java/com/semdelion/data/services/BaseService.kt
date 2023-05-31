package com.semdelion.data.services

import com.google.gson.GsonBuilder
import com.semdelion.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


abstract class BaseService {
    companion object {
        private var host = "https://newsdata.io/api/"

        private fun getLoggerInterceptor() : HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor()
            if(BuildConfig.DEBUG) {
                interceptor.level = HttpLoggingInterceptor.Level.BODY
            }
            return interceptor
        }

        private fun getAuthInterceptor() : Interceptor {
            return object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original: Request = chain.request()
                    val request: Request = original.newBuilder()
                        .header("X-ACCESS-KEY", "pub_21221020bb9c580281896a2305077b7ffbe0f")
                        .method(original.method, original.body)
                        .build()

                    return chain.proceed(request)
                }
            }
        }

        private val client = OkHttpClient.Builder()
            .addInterceptor(getLoggerInterceptor())
            .addInterceptor(getAuthInterceptor())
            .build()
        private val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
        val retrofit = Retrofit.Builder()
                .baseUrl(host)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }
}