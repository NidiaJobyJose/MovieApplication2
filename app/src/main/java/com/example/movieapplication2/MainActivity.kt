package com.example.movieapplication2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapplication2.HomeScreen
import com.example.movieapplication2.MovieNav
import com.example.movieapplication2.ui.theme.MovieApplication2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            //MyAppBar{
            MovieNav()
            //}

        }
        Log.d("mainactivity", "oncreate called")
    }

    override fun onStart() {
        super.onStart()
        Log.d("mainactivity", "onstart called")
    }
}


//New

@Composable
fun MyAppBar(content: @Composable () -> Unit){
    MovieApplication2Theme() {
        content
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieApplication2Theme() {
        HomeScreen()
    }
}

/*class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopAppBar {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background)
                {
                    MainContent(movieList = getMovies())
                }
            }
        }
    }
}

@Composable
fun TopAppBar(content: @Composable () -> Unit){

    var showMenu by remember {
        mutableStateOf(false)
    }

    MovieApplicationTheme {
        // A surface container using the 'background' color from the theme
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Movies")},
                    actions = {
                        IconButton(onClick = { showMenu = !showMenu }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                        }

                        DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                            DropdownMenuItem(onClick = {
                                Row {
                                    Icon(imageVector = Icons.Default.Favorite,
                                        contentDescription = "Favorites",
                                        modifier = Modifier.padding(4.dp)
                                    )
                                    Text(text = "Favorites",
                                        modifier = Modifier
                                            .padding(4.dp)
                                            .width(100.dp)
                                    )
                                }
                            }
                        }
                    }
                )
            }
        ){
            content()
        }
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(movie: Movie) {

    var more by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .height(
                if (!more) {
                    150.dp
                } else {
                    400.dp
                }
            )
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
                elevation = 6.dp
            ) {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "movie pic")
            }
            Column {
                Text(text = movie.title)
                Text(text = "Director: " + movie.director)
                Text(text = "Released: " + movie.year)

                AnimatedVisibility(
                    visible = more,
                    enter = fadeIn(
                        // Fade in with the initial alpha of 0.3f.
                        initialAlpha = 0.3f
                    ),
                    exit = fadeOut()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                    ){
                        Text("Plot: " + movie.plot, Modifier.fillMaxWidth())
                        Divider(startIndent = 5.dp)
                        Text("Genre: " + movie.genre, Modifier.fillMaxWidth())
                        Text("Actors: " + movie.actors, Modifier.fillMaxWidth())
                        Text("Rating: " + movie.rating, Modifier.fillMaxWidth())
                    }
                }

                Icon(imageVector = if (more) {
                    Icons.Default.KeyboardArrowUp
                } else {
                    Icons.Default.KeyboardArrowDown
                },
                    contentDescription = "arrowDown",
                    Modifier.clickable { more = !more })
            }
        }
    }
}

@Composable
fun MainContent(movieList: List<Movie>) {

    LazyColumn{
        items(movieList) { movie ->
            MovieRow(movie = movie)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TopAppBar {
        MainContent(movieList = getMovies())
    }
}*/

