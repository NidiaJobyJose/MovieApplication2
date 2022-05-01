package com.example.movieapplication2.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.example.movieapplication2.models.Movie
import com.example.movieapplication2.models.getMovies


@Preview(showBackground = true)
@Composable
fun FavouritesIcon(
    movie: Movie = getMovies()[0],
    isFav: Boolean = false,
    onFavClicked: (Movie) -> Unit = {},
){
    Column(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalAlignment = Alignment.End) {
        IconButton(
            modifier = Modifier.width(60.dp),
            onClick = { onFavClicked(movie) }
        ) {
            Icon(
                tint = MaterialTheme.colors.secondary,
                imageVector =
                if (isFav) Icons.Default.Favorite
                else Icons.Default.FavoriteBorder,
                contentDescription = "add to favorites")
        }
    }

}

@OptIn(ExperimentalMaterialApi::class, androidx.compose.animation.ExperimentalAnimationApi::class)
@Composable
fun MovieRow(
    movie: Movie = getMovies()[0],
    onItemClick: (String) -> Unit = {},
    content: @Composable () -> Unit = {} )  {

    val expanded = remember { mutableStateOf(false) }

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(2.dp)
        .clickable { onItemClick(movie.id) }
    )   {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = Modifier
                .size(100.dp)
                .padding(5.dp),
                shape = RectangleShape, elevation = 5.dp) {
                Image(painter = rememberImagePainter(
                    data = movie.images[0],
                    builder = {
                        transformations(CircleCropTransformation())
                    }
                ),
                    contentDescription = "Movie Profile Pic",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(5.dp)
                )
            }

            Column {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.body1)
                Text(text = "Released: "+movie.year, style = MaterialTheme.typography.body1)

                AnimatedVisibility(visible = expanded.value) {
                    Surface(modifier = Modifier
                        .padding(5.dp)) {
                        Column() {
                            Text(text = "Plot: ${movie.plot}")
                            Divider()
                            Text(text = "Genre: ${movie.genre}")
                            Text(text = "Actors: ${movie.actors}")
                            Text(text = "Rating: ${movie.rating}")
                        }
                    }
                }

                Surface(modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
                    .clickable { expanded.value = !expanded.value },
                    elevation = 5.dp,
                    shape = RoundedCornerShape(corner = CornerSize(6.dp))
                ) {
                    if(expanded.value){
                        Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Arrow Up")
                    } else {
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Arrow Down")
                    }
                }
            }
            content()
        }
    }
}

@Composable
fun HorizontalScrollableImageView(movie: Movie = getMovies()[0]){
    LazyRow {
        items(movie.images) { image ->
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .size(240.dp),
                elevation = 4.dp
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = image,
                        builder = {
                            transformations(RoundedCornersTransformation(5f))
                        }
                    ),
                    contentDescription = "Movie Image",
                    modifier = Modifier.fillMaxHeight()
                )
            }
        }
    }
}



