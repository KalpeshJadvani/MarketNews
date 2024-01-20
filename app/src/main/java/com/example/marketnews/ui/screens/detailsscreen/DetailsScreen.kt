package com.example.marketnews.ui.screens.detailsscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.marketnews.R
import com.example.marketnews.data.model.Article
import com.example.marketnews.utils.Constants


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(article: Article, navController: NavController){
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = article.author?:"No Author" )},
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                        }
                    }
                )
            },
            content = {
                PageContent(article)
            }
        )
    }

}

@Composable
fun PageContent(item: Article) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = item.urlToImage,
            placeholder = painterResource(R.drawable.image_placeholder),
            error = painterResource(R.drawable.image_placeholder),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Gray)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = item.title?:"No Tile", style = MaterialTheme.typography.displayMedium)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = item.description?:"No Description", style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun DetailsScreenPreview() {
    DetailsScreen(Constants.article, rememberNavController())
}