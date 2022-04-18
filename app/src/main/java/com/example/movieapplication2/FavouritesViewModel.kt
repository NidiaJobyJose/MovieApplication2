package com.example.movieapplication2

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.movieapplication2.models.Movie

class FavouritesViewModel : ViewModel() {
    private val _favMovies = mutableStateListOf<Movie>()

    fun getFavs() : List<Movie>{
        return _favMovies
    }

    fun addFav(movie: Movie){
        if(!_favMovies.contains(movie)) _favMovies.add(movie)

        for(movie in _favMovies){
            Log.i("FavouritesViewModel", movie.title)
        }

    }

    fun removeFav(movie: Movie){
        _favMovies.remove(movie)
    }

    fun exists(movie: Movie) : Boolean {
        return _favMovies.any {m -> m.id == movie.id}
    }

    fun isFavourite(movie: Movie) : Boolean {
        return exists(movie)
    }

}