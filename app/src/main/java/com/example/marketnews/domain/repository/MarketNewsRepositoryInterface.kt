package com.example.marketnews.domain.repository

import com.example.marketnews.data.model.ApiModel
import com.example.marketnews.utils.network.DataState
import kotlinx.coroutines.flow.Flow

interface MarketNewsRepositoryInterface {
    suspend fun getNews(): Flow<DataState<ApiModel>>
}