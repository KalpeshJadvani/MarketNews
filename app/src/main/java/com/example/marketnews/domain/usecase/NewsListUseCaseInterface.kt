package com.example.marketnews.domain.usecase

import com.example.marketnews.domain.model.NewsModel
import com.example.marketnews.utils.network.DataState

import kotlinx.coroutines.flow.Flow

interface NewsListUseCaseInterface {
    suspend fun getNewsUseCase(): Flow<DataState<List<NewsModel>>>
}