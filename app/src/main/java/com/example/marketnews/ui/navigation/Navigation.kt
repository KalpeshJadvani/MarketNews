package com.example.marketnews.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marketnews.data.model.Article
import com.example.marketnews.ui.screens.detailsscreen.DetailsScreen
import com.example.marketnews.ui.screens.mainscreen.MainScreen
import com.google.gson.Gson

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.HOME_SCREEN ){
        composable(Routes.HOME_SCREEN){
            MainScreen(navController)
        }

        composable(Routes.DETAILS_SCREEN) { backStackEntry ->
            val objectDataJson =  backStackEntry.arguments?.getString("objectData")
            val gson = Gson()
            val article = gson.fromJson(objectDataJson, Article::class.java)
            DetailsScreen(article, navController)
        }
    }

}