package com.example.marketnews.data.repository


import com.example.marketnews.data.datasource.remote.ApiService

import com.example.marketnews.data.model.ApiModel
import com.example.marketnews.domain.repository.MarketNewsRepositoryInterface
import com.example.marketnews.utils.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class MarketNewsRepository @Inject constructor(
    private val apiService: ApiService
) : MarketNewsRepositoryInterface {
    override suspend fun getNews(): Flow<DataState<ApiModel>> = flow {
        emit(DataState.Loading)
        try {
            val searchResult = apiService.getNews()
            emit(DataState.Success(searchResult))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}