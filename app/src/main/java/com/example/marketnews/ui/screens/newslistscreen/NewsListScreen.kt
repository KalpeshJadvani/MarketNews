package com.example.marketnews.ui.screens.newslistscreen


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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.example.marketnews.domain.model.NewsModel
import com.example.marketnews.ui.component.CircularIndeterminateProgressBar
import com.example.marketnews.ui.component.CustomScaffold
import com.example.marketnews.ui.component.ErrorPage
import com.example.marketnews.ui.component.text.TitleMedium
import com.example.marketnews.ui.theme.RowBackGround
import com.example.marketnews.utils.Constants.tagArticlesList
import com.example.marketnews.utils.Constants.tagCustomRow
import com.example.marketnews.utils.network.DataState


@Composable
fun NewsListScreen(
    navController: NavController
) {

    val mainViewModel = hiltViewModel<NewsListViewModel>()
    val dataState by mainViewModel.newsListState.collectAsState()
    val title: String = stringResource(R.string.app_name)

    LaunchedEffect(Unit) {
        mainViewModel.fetchNewsData()
    }


    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        CustomScaffold(title = title, onNavigateUp = { /*TODO*/ }) {
            dataState?.let {
                when (it) {
                    is DataState.Loading -> {
                        CircularIndeterminateProgressBar(
                            isDisplayed = true, 0.4f
                        )
                    }

                    is DataState.Success -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .testTag(tagArticlesList),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            items(it.data) { item ->
                                CustomRow(item, itemOkClick = { objectData ->
                                    mainViewModel.onNewsItemClick(objectData, navController)
                                })
                            }
                        }
                    }

                    is DataState.Error -> {
                        val error = it.exception
                        val newApiError = Throwable("$error")

                        ErrorPage(newApiError, onRetry = { ->
                            mainViewModel.fetchNewsData()
                            Log.d("MyLog", "Error ======> $error")
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun CustomRow(
    article: NewsModel, itemOkClick: (objectData: NewsModel) -> Unit
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


