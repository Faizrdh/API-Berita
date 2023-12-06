package com.example.BelajarAPI.ui.detailnews

import androidx.lifecycle.ViewModel
import com.example.BelajarAPI.data.NewsRepository

class DetailNewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    fun getHeadlineNews() = newsRepository.getHeadlineNews()
}