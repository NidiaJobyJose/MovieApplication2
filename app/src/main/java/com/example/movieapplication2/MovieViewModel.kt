package com.example.movieapplication2


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.movieapplication2.models.Movie

class MovieViwModel : ViewModel(){
    private var movies = mutableStateListOf<Movie>()

    fun addMovie(movie: Movie){
        movies.add(movie)
    }
    fun removeMovie(movie: Movie){
        movies.remove(movie)
    }
    fun getAllMovies(): List<Movie>{
        return movies
    }
    fun checkMovie(movie: Movie){
        movies.contains(movie)
    }
}