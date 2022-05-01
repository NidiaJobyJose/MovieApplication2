package com.example.movieapplication2.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapplication2.widgets.FavouritesIcon
import com.example.movieapplication2.widgets.MovieRow
import com.example.movieapplication2.TopAppBarDropdownMenu
import com.example.movieapplication2.models.Movie
import com.example.movieapplication2.models.getMovies
import com.example.movieapplication2.navigation.MovieScreens
import com.example.movieapplication2.viewmodels.FavouritesViewModel


@Preview(showBackground = true)
@Composable
fun HomeScreen(navController: NavController = rememberNavController(), viewModel: FavouritesViewModel = viewModel()){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Movies")
                }, actions = {
                    TopAppBarDropdownMenu(navController = navController)
                })
        }) {
        MainContent(navController = navController, viewModel = viewModel)
    }
}

@Composable
fun MainContent(navController: NavController,
                viewModel: FavouritesViewModel = viewModel(),
                movieList: List<Movie> = getMovies()){
    LazyColumn {
        items(movieList) { movie ->
            MovieRow(
                movie = movie,
                onItemClick = { movieId ->
                    navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")
                },
                content = {
                    FavouritesIcon(isFav = viewModel.isFavourite(movie), movie = movie){
                            m->
                        if(viewModel.isFavourite(m)){
                            viewModel.removeFav(m)
                        } else {
                            viewModel.addFav(m)
                        }
                    }
                }

            )
        /*{
                FavouritesIcon(isFav = viewModel.isFavourite(movie), movie = movie){
                        m->
                    if(viewModel.isFavourite(m)){
                        viewModel.removeFav(m)
                    } else {
                        viewModel.addFav(m)
                    }
                }
            }*/
        }
    }
}


