package com.example.newsapp.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.NewsAdapter
import com.example.newsapp.api.NewsApiService
import com.example.newsapp.api.RetrofitHelper
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.viewModel.NewsViewModel
import com.example.newsapp.viewModel.NewsViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var newsViewModel: NewsViewModel
    private val adapter = NewsAdapter()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter


        val newsApiService = RetrofitHelper.getInstance().create(NewsApiService::class.java)
        val newsRepository = NewsRepository(newsApiService)
        newsViewModel = ViewModelProvider(
            this,
            NewsViewModelFactory(newsRepository)
        ).get(NewsViewModel::class.java)
        newsViewModel.news.observe(this, Observer {
            Log.d("dataKalpesh", it.articles.toString())
            adapter.setMovies(it)
        })
        newsViewModel.getNews()
    }
}