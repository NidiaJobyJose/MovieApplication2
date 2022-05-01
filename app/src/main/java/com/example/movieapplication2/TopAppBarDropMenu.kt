package com.example.movieapplication2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapplication2.navigation.MovieScreens

@Composable
fun TopAppBarDropdownMenu(bodyContent: MutableState<String> = mutableStateOf(""), navController: NavController) {

    val expanded = remember { mutableStateOf(false) }
    Box(
        //Modifier.wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = {
            expanded.value = true
        }) {
            Icon(
                Icons.Filled.MoreVert,
                contentDescription = "Drop Down"
            )
        }
    }

    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
        modifier = Modifier.width(140.dp)
    ) {
        DropdownMenuItem(onClick = {
            expanded.value = false
            bodyContent.value = "First Item Selected"
        }) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(route = MovieScreens.FavouritesScreen.name)
                    }) {
                Row(verticalAlignment = Alignment.CenterVertically){
                    Icon(imageVector = Icons.Default.Favorite,
                        contentDescription = "Favourites Icon",
                        modifier = Modifier.size(20.dp))
                    Spacer(Modifier.width(5.dp))
                    Text(text = "Favourites")
                }
            }

        }
    }
}