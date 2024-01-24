package com.example.marketnews.data.datasource.remote

import com.example.marketnews.data.model.ApiModel
import retrofit2.http.GET


interface ApiService {
    @GET("/NewsAPI/top-headlines/category/health/in.json")
    suspend fun getNews(): ApiModel
}