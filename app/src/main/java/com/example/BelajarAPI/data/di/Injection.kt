package com.example.BelajarAPI.data.di

import android.content.Context
import com.example.BelajarAPI.data.NewsRepository
import com.example.BelajarAPI.data.local.room.NewsDb
import com.example.BelajarAPI.data.remote.retrofit.ApiConfig

object Injection {

    fun provideRepository(context: Context): NewsRepository {

        val apiService = ApiConfig.getApiService()
        val database = NewsDb.getInstance(context)
        val dao = database.newsDao()
        return NewsRepository.getInstance(apiService, dao)
    }
}