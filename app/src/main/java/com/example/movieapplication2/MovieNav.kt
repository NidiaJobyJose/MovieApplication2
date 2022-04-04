package com.example.movieapplication2

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun MovieNav(){
    val navController = rememberNavController()
    val myViewModel: MovieViwModel = viewModel()

    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name){

        composable(MovieScreens.HomeScreen.name){
            HomeScreen(navController = navController,
            //myViewModel = myViewModel
        )
        }

        composable(
            MovieScreens.DetailScreen.name + "/{movie}",
            arguments = listOf(navArgument("movie") {
                type = NavType.StringType
            })
            ){ backStackEntry ->


            DetailScreen(navController = navController,
                movieId = backStackEntry.arguments?.getString("movie"),
                //myViewModel = myViewModel
        )
        }

        composable(MovieScreens.FavScreen.name){
            FavScreen(navController = navController,
                //myViewModel = myViewModel
        ) }
    }

}