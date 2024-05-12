package com.example.movieappmad24.viewmodels

import com.example.movieappmad24.models.MovieWithImages

// Inherit from ViewModel class
/*class MoviesViewModel(
    private val repository: MovieRepository
) : ViewModel() {


    private val _movies = MutableStateFlow(listOf<Movie>())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()


    init {
        viewModelScope.launch {
            repository.getAllMovies().distinctUntilChanged()
                .collect{ listOfMovies ->
                    _movies.value = listOfMovies
                }
        }
    }
    fun toggleFavorite(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        viewModelScope.launch {
            repository.updateMovie(movie)
        }
    }
}*/

interface MoviesViewModel {
    //same usage in both classes, can use interface
    fun toggleFavorite(movie: MovieWithImages)
}