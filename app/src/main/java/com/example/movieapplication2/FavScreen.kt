package com.example.movieapplication2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.fhcampuswien.movieapplication.models.Movie
import at.ac.fhcampuswien.movieapplication.models.getMovies


@Composable
fun FavScreen(navController: NavController = rememberNavController(),
              myViewModel: MovieViwModel = viewModel()){

    //val movie = movieFilter(movieId = movieId)

    Scaffold(
        topBar = {
            TopAppBar(elevation = 3.dp){
                Row {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack() //go back
                        }
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "My Favourie Movies")
                }
            }
        }) {
        MainContent()

    }


}

@Composable
fun MainContent(movies: List<Movie> = getMovies().take(2)){

    LazyColumn{
        items(items = movies){ movie ->
            Surface(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()) {
                Column() {
                    MovieRow(movie = movie)

                   // Spacer(modifier = Modifier.height(8.dp))
                }

            }
            //navController.navigate(route = MovieScreens.FavScreen.name)
        }
    }



}
