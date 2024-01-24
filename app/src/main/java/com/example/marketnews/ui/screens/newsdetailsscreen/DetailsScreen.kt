package com.example.marketnews.ui.screens.newsdetailsscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.marketnews.R
import com.example.marketnews.domain.model.NewsModel
import com.example.marketnews.ui.component.CustomScaffold
import com.example.marketnews.utils.Constants.tagTile
import com.google.gson.Gson


@Composable
fun DetailsScreen(navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val objectDataJson = navBackStackEntry?.arguments?.getString("objectData")
    val gson = Gson()
    val article = gson.fromJson(objectDataJson, NewsModel::class.java)

    val author = article?.author ?: "No Author"
    val title = article?.title ?: "No Tile"
    val urlToImage = article?.urlToImage ?: "No Image"
    val description = article?.description ?: "No Description"

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        CustomScaffold(title = author, onNavigateUp = { navController.popBackStack() }, content = {
            PageContent(title = title, urlToImage = urlToImage, description = description)
        })
    }

}

@Composable
fun PageContent(title: String, urlToImage: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = urlToImage,
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

        Text(
            text = title,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.testTag(tagTile)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = description, style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun DetailsScreenPreview() {
    DetailsScreen(rememberNavController())
}