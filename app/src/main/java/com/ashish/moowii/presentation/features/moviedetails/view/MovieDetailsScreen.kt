package com.ashish.moowii.presentation.features.moviedetails.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ashish.moowii.presentation.features.moviedetails.state.MovieDetailState
import com.ashish.moowii.presentation.features.moviedetails.state.MovieDetailStateData
import com.ashish.moowii.presentation.features.moviedetails.viewModel.MovieDetailViewModel

@Composable
fun MovieDetailsScreen(id: Int, navController: NavHostController) {
    val viewModel: MovieDetailViewModel = hiltViewModel()
    viewModel.getMovieDetails(id)
    val context = LocalContext.current
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel
            .detailErrorState
            .collect { message ->
                Toast.makeText(
                    context,
                    message,
                    Toast.LENGTH_SHORT,
                ).show()
            }
    }
    LaunchedEffect(Unit) {
        viewModel
            .uiState
            .collect {
                if (it is MovieDetailState.Success) {
                    Toast.makeText(
                        context,
                        it.movie.detailTitle,
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }
    MovieDetailsView(uiState.value) {
        navController.popBackStack()
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailsView(movieDetailState: MovieDetailState, onBack: () -> Unit) {
    Scaffold {
        Column() {
            TopAppBar(
                title = {
                    Text(text = if (movieDetailState is MovieDetailState.Success) movieDetailState.movie.detailTitle else "")
                },
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = {
                    IconButton(onClick = {
                        onBack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "backIcon")
                    }
                },
                actions = {},
                backgroundColor = MaterialTheme.colors.primary,
                elevation = 10.dp
            )
            when (movieDetailState) {
                is MovieDetailState.Error -> Text(text = movieDetailState.error)
                MovieDetailState.Loading -> Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
                is MovieDetailState.Success ->
                    Body(value = movieDetailState.movie)
            }
        }
    }
}

@Composable
fun Body(value: MovieDetailStateData) {
    Column() {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = value.detailBackdropPath,
            contentDescription = "${value.detailBackdropPath} Poster"
        )
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {

            Spacer(Modifier.height(10.dp))
            Text(
                value.detailTitle,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(10.dp))
            Text(
                value.detailOverview
            )
            Spacer(Modifier.height(10.dp))
            Text(
                "Ratings: " + value.detailVoteAverage.toString(),
            )
        }
    }
}