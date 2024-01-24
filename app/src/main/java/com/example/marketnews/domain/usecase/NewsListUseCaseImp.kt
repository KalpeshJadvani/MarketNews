package com.example.marketnews.domain.usecase


import com.example.marketnews.data.model.ApiModel
import com.example.marketnews.data.repository.MarketNewsRepository
import com.example.marketnews.domain.model.NewsModel
import com.example.marketnews.utils.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class NewsListUseCaseImp @Inject constructor(
    private val repo: MarketNewsRepository
) : NewsListUseCaseInterface {

    override suspend fun getNewsUseCase(): Flow<DataState<List<NewsModel>>> {

        return repo.getNews().map { apiDataState ->
            when (apiDataState) {
                is DataState.Loading -> DataState.Loading
                is DataState.Success -> {
                    val newsModelList = transformApiModelToNewsModel(apiDataState.data)
                    DataState.Success(newsModelList)
                }

                is DataState.Error -> {
                    DataState.Error(apiDataState.exception)
                }

            }
        }
    }

    private fun transformApiModelToNewsModel(apiModel: ApiModel): List<NewsModel> {
        val article = apiModel.articles;

        return article.map {
            NewsModel(it.title, it.urlToImage, it.author, it.description)
        }
    }

}