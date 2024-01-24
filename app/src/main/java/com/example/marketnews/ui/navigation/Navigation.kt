package com.example.marketnews.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marketnews.ui.screens.newsdetailsscreen.DetailsScreen
import com.example.marketnews.ui.screens.newslistscreen.NewsListScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.HOME_SCREEN ){
        composable(Routes.HOME_SCREEN){
            NewsListScreen(navController)
        }

        composable(Routes.DETAILS_SCREEN) {
            DetailsScreen(navController)
        }
    }

}