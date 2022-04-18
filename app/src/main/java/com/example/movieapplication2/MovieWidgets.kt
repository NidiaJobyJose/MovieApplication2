package com.example.movieapplication2

import android.util.Log
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
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.example.movieapplication2.models.Movie
import com.example.movieapplication2.models.getMovies


//@Preview(showBackground = true)
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

@Composable
fun FavouritesIcon(isFav: Boolean, movie: Movie, code: (Movie) -> Unit = {}){
    Column(horizontalAlignment = Alignment.End,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        IconButton(
            onClick = {
                code(movie)
            }
        ) {
            if(isFav){
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Make Favourite Button Filled"
                )
            } else {
                Icon(
                    Icons.Filled.FavoriteBorder,
                    contentDescription = "Make Favourite Button NOT Filled"
                )
            }
        }
    }
}
/*import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.movieapplication2.models.Movie
import com.example.movieapplication2.models.getMovies
import coil.compose.AsyncImage
import coil.request.ImageRequest

//import coil.request.ImageRequest
//import com.android.volley.toolbox.ImageRequest




@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(
    movie: Movie = getMovies()[0],
    onItemClick: (String) -> Unit = {}) {

    var showDesc by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .heightIn(min = 130.dp)
            .clickable {
                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),

                ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .build(),
                    contentDescription = "Displaying Pic",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(64.dp).clip(CircleShape)
                )
            }


            /*
            AsyncImage(
                model = movie.images[0],
                contentDescription = null
            )*/

            Column {

                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "Director: ${movie.director}",
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = "Released Year: ${movie.year}",
                    style = MaterialTheme.typography.caption
                )

                /*Icon(imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorites",
                    modifier = Modifier.padding(4.dp))*/

                if (showDesc) {
                    AnimatedVisibility(
                        visible = showDesc,
                        modifier = Modifier
                            .padding(10.dp),
                        enter = slideInVertically(),
                        exit = slideOutVertically()

                    ) {

                        Column {
                            Text(
                                text = "Plot: ${movie.plot}",
                                style = MaterialTheme.typography.caption,
                            )
                            Divider(
                                modifier = Modifier.padding(5.dp)
                            )

                            Text(
                                text = "Genre: ${movie.genre}",
                                style = MaterialTheme.typography.caption
                            )
                            Text(
                                text = "Actors: ${movie.actors}",
                                style = MaterialTheme.typography.caption
                            )
                            Text(
                                text = "Rating: ${movie.rating}",
                                style = MaterialTheme.typography.caption
                            )
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "Arrow Down",
                                modifier = Modifier.clickable { showDesc = !showDesc }
                            )
                        }

                    }
                } else {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Arrow Up",
                        modifier = Modifier.clickable { showDesc = !showDesc }
                    )
                }
            }
        }
    }
}

@Composable
fun HorizontalScrollImages (movie: Movie = getMovies()[0]){
    LazyRow{
        items(movie.images){ image ->
            
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .height(250.dp),
                elevation = 4.dp
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                    .data(image)

                    .build(),
                    contentDescription = "Movie Images",
                    modifier = Modifier.clip(RoundedCornerShape(5.dp))
                )

            }
        }
    }
}*/
