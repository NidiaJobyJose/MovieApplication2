package com.example.movieapplication2

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.movieapplication2.feed.MoviesScreen
import com.example.movieapplication2.feed.MoviesScreenViewModel


@ExperimentalComposeUiApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun Navigation(
    navController: NavHostController,
    cardsViewModel: MoviesScreenViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MoviesFeed.route,
        modifier = Modifier.fillMaxSize(),

        ) {
        composable(Screen.MoviesFeed.route) {
            MoviesScreen(
                onNavigate = navController::navigate,
                onNavigateUp = {},
                viewModel = cardsViewModel
            )
        }
        composable(Screen.FavouriteScreen.route) {
            FavouriteMoviesScreen(
                onNavigate = navController::navigate,
                onNavigateUp = {navController.popBackStack()},
                viewModel = cardsViewModel
            )
        }
        composable(
            route = MovieScreens.DetailScreen.route + "/{movieId}",
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.StringType
                },
            )
        ) {
            val movieId = it.arguments?.getString("movieId")!!
            MovieDetailScreen(
                onNavigateUp = navController::navigateUp,
                movieId = movieId,
                viewModel = cardsViewModel
            )
        }
    }
}
/*
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

}*/