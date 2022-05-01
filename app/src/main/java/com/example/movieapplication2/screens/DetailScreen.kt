package com.example.movieapplication2.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapplication2.widgets.FavouritesIcon
import com.example.movieapplication2.widgets.HorizontalScrollableImageView
import com.example.movieapplication2.widgets.MovieRow
import com.example.movieapplication2.models.Movie
import com.example.movieapplication2.models.getMovies
import com.example.movieapplication2.viewmodels.FavouritesViewModel

fun filterMove(movieId: String?) : Movie{
    return getMovies().filter { it.id == movieId }[0]
}


@Preview(showBackground = true)
@Composable
fun DetailScreen(movieId: String? = getMovies()[0].id, navController: NavController = rememberNavController(), viewModel: FavouritesViewModel = viewModel()){

    val movie = filterMove(movieId = movieId)

    Scaffold(
        topBar = {
            TopAppBar{
                Row(verticalAlignment = Alignment.CenterVertically){
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = movie.title, style = MaterialTheme.typography.h6)
                }
            }
        }) {
        MainContent(movie, viewModel)
    }
}

@Composable
fun MainContent(movie: Movie, viewModel: FavouritesViewModel){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()){
        Column{
            MovieRow(movie = movie){
                FavouritesIcon(isFav = viewModel.isFavourite(movie), movie = movie)
                { m->
                    if(viewModel.isFavourite(m)){
                        viewModel.removeFav(m)
                    } else {
                        viewModel.addFav(m)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Text(text = "Movie Images",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            HorizontalScrollableImageView(movie = movie)
        }
    }
}



