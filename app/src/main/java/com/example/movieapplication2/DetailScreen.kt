package com.example.movieapplication2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.movieapplication2.feed.MoviesScreenViewModel

@ExperimentalCoilApi
@Composable
fun MovieDetailScreen(
    onNavigateUp: () -> Unit = {},
    viewModel: MoviesScreenViewModel,
    movieId: String
) {
    val expandedCardIds = viewModel.expandedCardIdsList
    val movie = viewModel.getItem(movieId)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        StandardToolbar(
            backgroundColor = Color(R.color.purple_200),
            onNavigateUp = onNavigateUp,
            title = {
                Text(
                    text = movie?.title ?: "Movie Detail",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = true,
        )

        Column(modifier = Modifier.fillMaxSize()) {
            movie?.let {
                ExpandableMovieCard(
                    cardModel = movie,
                    onCardArrowClick = { viewModel.onCardArrowClicked(movie.id) },
                    expanded = expandedCardIds.value.contains(movie.id),
                    onLikeClick = { it ->
                        viewModel.likeClick(it)
                    },
                    onItemClick = {}
                )
                Row {
                    LazyRow {
                        items(movie.images.size) { i ->
                            Image(
                                painter = rememberImagePainter(
                                    data = movie.images[i],
                                    imageLoader = ImageLoader.invoke(LocalContext.current)
                                ),
                                contentDescription = "Image",
                                modifier = Modifier
                                    .height(250.dp)
                                    .width(250.dp)
                                    .padding(8.dp)
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colors.onSurface,
                                    ),
                                contentScale = ContentScale.None
                            )
                        }
                    }
                }
            }
        }
    }
}

/* import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapplication2.models.Movie
import com.example.movieapplication2.models.getMovies


@Preview(showBackground = true)
@Composable
fun DetailScreen(
    navController: NavController = rememberNavController(),
    movieId: String? = "t0499549",
    //myViewModel: MovieViwModel = viewModel()
    ){

    val movie = movieFilter(movieId = movieId)


    Scaffold(
        topBar = {
            TopAppBar(elevation = 3.dp){
                Row {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = movie.title)
                }
            }
        }) {
        MainContent(movie = movie)
    }
}

@Composable
fun MainContent(movie: Movie){

    Surface(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            MovieRow(movie = movie)

            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Text(text = "Movie Images",
                style = MaterialTheme.typography.h5
            )

            HorizontalScrollImages(movie = movie)

        }

    }

}

fun movieFilter(movieId: String?) : Movie {
    return getMovies().filter { movie -> movie.id == movieId }[0]
} */