package com.ashish.moowii.presentation.features.movielist.view

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ashish.moowii.domain.model.MovieDomainModel
import com.ashish.moowii.presentation.features.movielist.viewModel.MovieListViewModel
import com.ashish.moowii.presentation.navigation.Screen

@Composable
fun MovieListScreen(navController: NavHostController) {
    val viewModel: MovieListViewModel = hiltViewModel()
    viewModel.fetchMoviesList()

    val moviePagingItems: LazyPagingItems<MovieDomainModel> =
        viewModel.uiState.collectAsLazyPagingItems()

    MovieListView(moviePagingItems, onMovieItemTap = {
        navController.navigate(route = Screen.MovieDetails.withMovieId(it))
    }, onSearchTap = {
        navController.navigate(route = Screen.MovieSearch.route)
    })
}


