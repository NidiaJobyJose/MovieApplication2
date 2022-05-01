package com.example.movieapplication2.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapplication2.screens.DetailScreen
import com.example.movieapplication2.screens.FavouritesScreen
import com.example.movieapplication2.screens.HomeScreen
import com.example.movieapplication2.viewmodels.FavouritesViewModel


@Composable
fun MovieNav(){
    val navController = rememberNavController() //NavController instance created

    val favViewModel: FavouritesViewModel = viewModel()

    // Navigation HomeScreen
    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name){
        composable(route = MovieScreens.HomeScreen.name) {
            HomeScreen(navController, favViewModel)
        }
    //Navigation DetailScreen
        composable(
            route= MovieScreens.DetailScreen.name + "/{movieId}", //placeholder for arguments
            arguments = listOf(navArgument("movieId"){ //define arguments that can be passed
                type = NavType.StringType
            })
        ) {
                backStackEntry ->
            DetailScreen(
                navController = navController,
                movieId = backStackEntry.arguments?.getString("movieId"), //pass value of movieId argument to DetailScreen composable
                viewModel = favViewModel)
        }
    //Navigation FavouriteScreen
        composable(route= MovieScreens.FavouritesScreen.name) {
            FavouritesScreen(navController, favViewModel)
        }
    }
}

