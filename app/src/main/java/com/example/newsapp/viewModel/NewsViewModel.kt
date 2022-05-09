package com.example.newsapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel(){

    val news = MutableLiveData<NewsResponse>()
    var job: Job? = null

    fun getNews(){
       job = CoroutineScope(Dispatchers.IO).launch{
           val response = newsRepository.getNews("us")
           if (response.isSuccessful){
               news.postValue(response.body())
           }
       }

    }
}