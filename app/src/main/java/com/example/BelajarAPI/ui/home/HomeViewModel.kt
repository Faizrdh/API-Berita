package com.example.BelajarAPI.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.BelajarAPI.data.NewsRepository
import com.example.BelajarAPI.data.local.entity.NewsEntity
import kotlinx.coroutines.launch

class HomeViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    fun getHeadlineNews() = newsRepository.getHeadlineNews()

    fun saveNews(news: NewsEntity) {
        viewModelScope.launch {
            newsRepository.setBookmark(news, true)
        }
    }

    fun delete(news: NewsEntity) {
        viewModelScope.launch {
            newsRepository.setBookmark(news, false)
        }
    }
}