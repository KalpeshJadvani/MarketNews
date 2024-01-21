package com.example.marketnews.ui.screens.mainscreen

import FakeMarketNewsRepository
import com.example.marketnews.data.model.ApiModel
import com.example.marketnews.assets.Constants
import com.example.marketnews.assets.Constants.netWorkError
import com.example.marketnews.utils.network.DataState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private lateinit var fakeRepository: FakeMarketNewsRepository

    @Before
    fun setup() {
        fakeRepository = FakeMarketNewsRepository();
    }

    @Test
    fun `getNews should emit success state with the correct data`() {
        val expectedData = Constants.apiModel

        runBlocking {
            fakeRepository.getNews().collect { dataState ->

                assertEquals((dataState as DataState.Success<ApiModel>).data, expectedData)
            }
        }
    }


    @Test
    fun `setShouldReturnNetworkError should affect the emitted state`() {

        fakeRepository.setShouldReturnNetworkError(true)

        runBlocking {
            fakeRepository.getNews().collect { dataState ->

                assertEquals(dataState, DataState.Error(netWorkError))
            }
        }
    }

}