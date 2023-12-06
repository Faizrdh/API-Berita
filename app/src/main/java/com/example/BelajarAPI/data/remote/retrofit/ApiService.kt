package com.example.BelajarAPI.data.remote.retrofit

import com.example.BelajarAPI.data.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines?country=us")
    suspend fun getNews(
        @Query("apiKey") apikey: String
    ): NewsResponse
}