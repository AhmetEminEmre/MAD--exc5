package com.example.movieappmad24.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieWithImages
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class WatchlistScreenViewModel (
    private val repository: MovieRepository
) : ViewModel(), MoviesViewModel {
    private val _movies = MutableStateFlow(listOf<MovieWithImages>())
    val movies: StateFlow<List<MovieWithImages>> = _movies.asStateFlow()


    init {
        viewModelScope.launch {
            repository.getAllMovies().distinctUntilChanged()
                .collect{ listOfMovies ->
                    _movies.value = listOfMovies
                }
        }
    }

    override fun toggleFavorite(movie: MovieWithImages) {
        movie.movie.isFavorite = !movie.movie.isFavorite
        viewModelScope.launch {
            repository.updateMovie(movie.movie)
        }
    }
}