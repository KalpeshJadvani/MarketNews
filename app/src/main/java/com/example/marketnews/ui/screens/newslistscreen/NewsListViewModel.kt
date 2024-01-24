package com.example.marketnews.ui.screens.newslistscreen


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.marketnews.domain.model.NewsModel
import com.example.marketnews.domain.usecase.NewsListUseCaseImp
import com.example.marketnews.ui.navigation.Routes
import com.example.marketnews.utils.network.DataState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val repo: NewsListUseCaseImp
) : ViewModel() {


    private val _newsListState: MutableStateFlow<DataState<List<NewsModel>>?> =
        MutableStateFlow(DataState.Loading)
    val newsListState = _newsListState.asStateFlow()

    fun fetchNewsData() {
        viewModelScope.launch {
            repo.getNewsUseCase().onEach { dataState ->
                _newsListState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    fun onNewsItemClick(newsModel: NewsModel, navController: NavController) {
        val gson = Gson()
        val objectDataJson = gson.toJson(newsModel, NewsModel::class.java)
        navController.navigate(
            Routes.DETAILS_SCREEN.replace("{objectData}", "$objectDataJson")
        )
    }
}