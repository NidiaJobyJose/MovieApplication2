package com.example.movieapplication2


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import at.ac.fhcampuswien.movieapplication.models.Movie
//import com.example.vomovie.models.Movie

class MovieViwModel : ViewModel(){
    private var movies = mutableStateListOf<Movie>()

    fun addMovie(movie: Movie){
        movies.add(movie)
    }
    fun removeMovie(movie:Movie){
        movies.remove(movie)
    }
    fun getAllMovies(): List<Movie>{
        return movies
    }
    fun checkMovie(movie: Movie){
        movies.contains(movie)
    }
}