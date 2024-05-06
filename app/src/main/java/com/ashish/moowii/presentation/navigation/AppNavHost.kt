package com.ashish.moowii.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ashish.moowii.presentation.features.moviedetails.view.MovieDetailsScreen
import com.ashish.moowii.presentation.features.movielist.view.MovieListScreen
import com.ashish.moowii.presentation.features.search.view.MovieSearchScreen
import com.ashish.moowii.utils.Constants

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Screen.MovieList.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.MovieList.route) {
            MovieListScreen(navController)
        }
        composable(Screen.MovieSearch.route) {
            MovieSearchScreen(navController)
        }
        composable(
            route = Screen.MovieDetails.route,
            arguments = listOf(navArgument(Constants.MOVIE_DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt(Constants.MOVIE_DETAILS_ARGUMENT_KEY)
                ?.let { MovieDetailsScreen(it,navController) }
        }
    }
}