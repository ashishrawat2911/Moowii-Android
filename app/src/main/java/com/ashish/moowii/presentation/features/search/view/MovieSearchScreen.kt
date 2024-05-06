package com.ashish.moowii.presentation.features.search.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ashish.moowii.domain.model.MovieDomainModel
import com.ashish.moowii.presentation.features.search.viewmodel.SearchMoviesViewModel
import com.ashish.moowii.presentation.navigation.Screen
import com.ashish.moowii.presentation.theme.movieCardColor
import com.ashish.moowii.utils.Constants

@Composable
fun MovieSearchScreen(navController: NavHostController) {
    val searchMoviesViewModel: SearchMoviesViewModel = hiltViewModel()
    val results = searchMoviesViewModel.uiState.collectAsState()
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        searchMoviesViewModel
            .detailErrorState
            .collect { message ->
                Toast.makeText(
                    context,
                    message,
                    Toast.LENGTH_SHORT,
                ).show()
            }
    }
    SearchScreen(
        searchQuery = searchQuery,
        results.value,
        onMovieItemTap = {
            navController.navigate(route = Screen.MovieDetails.withMovieId(it))
        },
        onSearchQueryChange = {
            searchMoviesViewModel.searchMovies(it);
            searchQuery = it;
        },
        onBack = {
            navController.popBackStack()
        })
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    searchQuery: String,
    searchResults: List<MovieDomainModel>,
    onMovieItemTap: (id: Int) -> Unit,
    onSearchQueryChange: (String) -> Unit, onBack: () -> Unit
) {
    Scaffold(topBar = {
        SearchBar(
            query = searchQuery,
            onQueryChange = onSearchQueryChange,
            onSearch = {},
            placeholder = {
                Text(text = "Search movies")
            },
            leadingIcon = {
                Icon(
                    modifier = Modifier.clickable {
                        onBack()
                    },
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            },
            trailingIcon = {},
            content = {
                LazyColumn(
                ) {
                    items(
                        count = searchResults.size,
                        key = { index -> searchResults[index].id },
                        itemContent = { index ->
                            val movie = searchResults[index]
                            Card(
                                backgroundColor = movieCardColor,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .height(80.dp)
                                    .clickable {
                                        onMovieItemTap(movie.id)
                                    },
                            ) {
                                Row {
                                    AsyncImage(
                                        model = Constants.movieImagePath + movie.posterPath,
                                        contentDescription = "${movie.title} Poster",
                                        modifier = Modifier.height(80.dp)
                                    )
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentHeight()
                                            .padding(horizontal = 10.dp),
                                    ) {
                                        Spacer(modifier = Modifier.height(5.dp))
                                        androidx.compose.material.Text(
                                            movie.title,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))
                                        androidx.compose.material.Text(
                                            movie.voteAverage.toString(),
                                            fontSize = 16.sp,
                                        )
                                    }
                                }
                            }
                        }
                    )
                }
            },
            active = true,
            onActiveChange = {},
            tonalElevation = 0.dp,
        )
    }) {

    }
}