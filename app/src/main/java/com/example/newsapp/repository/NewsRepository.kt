package com.example.newsapp.repository

import com.example.newsapp.api.NewsApiService

class NewsRepository(private val newsApiService: NewsApiService) {

    suspend fun getNews(countryCode: String) = newsApiService.getNews(countryCode)
}