package com.example.movieapplication2

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun FavouritesScreen(navController: NavController = rememberNavController(), viewModel: FavouritesViewModel){

    val favs = viewModel.getFavs()

    for(movie in favs){
        Log.i("FavouritesScreen", movie.title)
    }

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
                    Text(text = "My Favourite Movies", style = MaterialTheme.typography.h6)
                }
            }
        }) {
        LazyColumn {
            items(favs) { movie ->
                MovieRow(
                    movie = movie,
                    onItemClick = {
                            movieId -> navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId") }
                ) {
                }
            }
        }
    }
}

/*import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import coil.annotation.ExperimentalCoilApi

@ExperimentalCoilApi
@Composable
fun FavouriteMoviesScreen(
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: MoviesScreenViewModel
) {
    val favouriteMovies = viewModel.favouriteMovies.value
    val expandedCardIds = viewModel.expandedCardIdsList
    Column(
        modifier = Modifier.fillMaxWidth().background(Color.White)
    ) {
        StandardToolbar(
            backgroundColor = Color(R.color.purple_200),
            onNavigateUp = onNavigateUp,
            title = {
                Text(
                    text = "Favourite Movies",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = true,

            )

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                items(favouriteMovies.size) { i ->
                    val movie = favouriteMovies[i]
                    ExpandableMovieCard(
                        cardModel = movie,
                        onCardArrowClick = { viewModel.onCardArrowClicked(movie.id) },
                        expanded = expandedCardIds.value.contains(movie.id),
                        onLikeClick = { it ->
                            viewModel.likeClick(it)
                        },
                        onItemClick = { it ->
                            onNavigate(Screen.DetailScreen.route + "/$it")
                        },
                        showFavoriteIcon = false
                    )
                }
            }
        }
    }
}
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
import com.example.movieapplication2.models.Movie
import com.example.movieapplication2.models.getMovies


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
                    Text(text = "My Favourite Movies")
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

                }

            }
        }
    }



}
*/