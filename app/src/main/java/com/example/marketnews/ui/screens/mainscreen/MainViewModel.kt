package com.example.marketnews.ui.screens.mainscreen


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketnews.data.model.ApiModel
import com.example.marketnews.data.repository.MarketNewsRepository
import com.example.marketnews.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: MarketNewsRepository
) : ViewModel() {
    val apiResponse: MutableState<DataState<ApiModel>?> = mutableStateOf(null)

    init {
        fetchNewsData()
    }

    fun fetchNewsData() {

        viewModelScope.launch {
            repo.getNews().onEach {
                apiResponse.value = it
                delay(1000)
            }.launchIn(viewModelScope)
        }

    }
}