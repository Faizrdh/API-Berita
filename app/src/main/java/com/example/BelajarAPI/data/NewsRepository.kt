package com.example.BelajarAPI.data

import android.util.Log
import androidx.lifecycle.*
import com.example.BelajarAPI.BuildConfig
import com.example.BelajarAPI.data.local.entity.NewsEntity
import com.example.BelajarAPI.data.local.room.NewsDao
import com.example.BelajarAPI.data.remote.retrofit.ApiService

class NewsRepository private constructor(
    private val apiService: ApiService,
    private val newDao: NewsDao,
){

    fun getHeadlineNews() : LiveData<Result<List<NewsEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getNews(BuildConfig.API_KEY)
            val articles = response.articles
            val newsList = articles.map { article ->
                val isBookmarked = newDao.isNewsBookmarked(article.title)
                NewsEntity(
                    article.title,
                    article.publishedAt,
                    article.urlToImage,
                    article.url,
                    isBookmarked,
                )
            }
            newDao.deleteAll()
            newDao.insertNews(newsList)
        } catch (e: java.lang.Exception) {
            Log.d("NewsRepository", "getNews: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
        val localData: LiveData<Result<List<NewsEntity>>> = newDao.getNews().map {Result.Success(it) }
        emitSource(localData)
    }

    fun getBookmarked() : LiveData<List<NewsEntity>> {
        return newDao.getBookmarkedNews()
    }

    suspend fun setBookmark(news: NewsEntity, state: Boolean) {
        news.isBookmarked = state
        newDao.updateNews(news)
    }

    companion object {
        @Volatile
        private var instance: NewsRepository? = null
        fun getInstance(
            apiService: ApiService,
            newDao: NewsDao,
        ): NewsRepository =
            instance ?: synchronized(this) {
                instance ?: NewsRepository(apiService, newDao)
            }.also { instance = it }
    }
}