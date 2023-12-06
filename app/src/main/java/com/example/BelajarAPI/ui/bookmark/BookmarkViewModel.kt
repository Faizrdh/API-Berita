package com.example.BelajarAPI.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.BelajarAPI.data.NewsRepository
import com.example.BelajarAPI.data.local.entity.NewsEntity
import kotlinx.coroutines.launch

class BookmarkViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    fun getBookmark() = newsRepository.getBookmarked()

    fun delete(news: NewsEntity) {
        viewModelScope.launch {
            newsRepository.setBookmark(news, false)
        }
    }
}