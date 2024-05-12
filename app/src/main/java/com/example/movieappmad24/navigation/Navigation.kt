package com.example.movieappmad24.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad24.data.MovieDatabase
import com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.screens.DetailScreen
import com.example.movieappmad24.screens.HomeScreen
import com.example.movieappmad24.screens.WatchlistScreen
import com.example.movieappmad24.viewmodels.DetailScreenViewModel
import com.example.movieappmad24.viewmodels.HomeScreenViewModel
import com.example.movieappmad24.viewmodels.MoviesViewModelFactory
import com.example.movieappmad24.viewmodels.WatchlistScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Composable
fun Navigation() {
    val navController = rememberNavController() // create a NavController instance

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val db = MovieDatabase.getDatabase(context, coroutineScope)
    val repository = MovieRepository(movieDao = db.movieDao())

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            val factory = MoviesViewModelFactory(repository = repository, Id = "")
            HomeScreen(movieId = "", navController = navController)
        }

        composable(
            route = Screen.DetailScreen.route,
            arguments = listOf(navArgument(DETAIL_ARGUMENT_KEY) { type = NavType.StringType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString(DETAIL_ARGUMENT_KEY) ?: ""
            val factory = MoviesViewModelFactory(repository = repository, Id = movieId)
            DetailScreen(movieId = movieId, navController = navController)
        }

        composable(route = Screen.WatchlistScreen.route) {
            val factory = MoviesViewModelFactory(repository = repository, Id = "")
            WatchlistScreen(movieId = "", navController = navController)
        }
    }
}
