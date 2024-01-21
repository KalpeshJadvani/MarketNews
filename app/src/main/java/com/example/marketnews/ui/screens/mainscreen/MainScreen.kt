package com.example.marketnews.ui.screens.mainscreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.marketnews.R
import com.example.marketnews.data.model.ApiModel
import com.example.marketnews.data.model.Article
import com.example.marketnews.ui.component.CircularIndeterminateProgressBar
import com.example.marketnews.ui.component.ErrorPage
import com.example.marketnews.ui.component.text.TitleMedium
import com.example.marketnews.ui.navigation.Routes
import com.example.marketnews.ui.theme.RowBackGround
import com.example.marketnews.utils.Constants.tagArticlesList
import com.example.marketnews.utils.Constants.tagCustomRow
import com.example.marketnews.utils.network.DataState
import com.google.gson.Gson


@Composable
fun MainScreen(
    navController: NavController
) {

    val mainViewModel = hiltViewModel<MainViewModel>()
    val newsList = remember { mutableStateOf(arrayListOf<Article>()) }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        mainViewModel.apiResponse.value?.let {

            if (it is DataState.Loading) {
                CircularIndeterminateProgressBar(
                    isDisplayed = mainViewModel.apiResponse.value is DataState.Loading, 0.4f
                )
            } else if (it is DataState.Success<ApiModel>) {
                newsList.value =
                    (mainViewModel.apiResponse.value as DataState.Success<ApiModel>).data.articles as ArrayList

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag(tagArticlesList),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    items(newsList.value) { item ->
                        CustomRow(item, itemOkClick = { objectData -> //This is the passed object
                            val gson = Gson()
                            val objectDataJson = gson.toJson(objectData, Article::class.java)
                            Log.d(
                                "MyLog", "Send obj from MainScree, author: ${objectData.author}"
                            )
                            navController.navigate(
                                Routes.DETAILS_SCREEN.replace(
                                    "{objectData}", "$objectDataJson"
                                )
                            )
                        })
                    }

                }
            } else if (it is DataState.Error) {
                val error = mainViewModel.apiResponse.value
                val newApiError = Throwable("$error")

                ErrorPage(newApiError, onRetry = { ->
                    mainViewModel.fetchNewsData()
                    Log.d("MyLog", "Error ======> $error")
                })
            }
        }
    }
}

@Composable
fun CustomRow(
    article: Article, itemOkClick: (objectData: Article) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(RowBackGround)
            .fillMaxWidth()
            .height(100.dp)
            .clickable(onClick = { itemOkClick(article) })
            .testTag(tagCustomRow), verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(start = 6.dp)
                .clip(RoundedCornerShape(8.dp))
                .size(90.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(200.dp)
                    .background(Color.DarkGray),
                placeholder = painterResource(R.drawable.image_placeholder),
                model = article.urlToImage,
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                error = painterResource(R.drawable.image_placeholder),
            )
        }
        Column {
            Text(
                text = article.title ?: "No Tile",
                modifier = Modifier
                    .padding(start = 10.dp, top = 3.dp)
                    .weight(2f),
                maxLines = 2,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis
            )
            Box(
                modifier = Modifier
                    .height(30.dp)
                    .align(Alignment.End)
                    .weight(1f),

                ) {
                Spacer(
                    modifier = Modifier
                        .padding(start = 5.dp, end = 5.dp)
                        .height(2.dp)
                        .fillMaxWidth()
                        .background(Color.DarkGray),
                )
                TitleMedium(
                    text = article.author ?: "No Author",
                    modifier = Modifier.padding(start = 10.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = Color.Unspecified,
                    )
                )
            }
        }
    }
}


