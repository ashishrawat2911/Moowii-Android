package com.ashish.moowii.presentation.features.movielist.view

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.ashish.moowii.R
import com.ashish.moowii.domain.model.MovieDomainModel
import com.ashish.moowii.presentation.components.ErrorMessage
import com.ashish.moowii.presentation.components.LoadingNextPageItem
import com.ashish.moowii.presentation.components.PageLoader
import com.ashish.moowii.presentation.theme.movieCardColor
import com.ashish.moowii.presentation.theme.whiteColor
import com.ashish.moowii.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieListView(
    movieListState: LazyPagingItems<MovieDomainModel>,
    onMovieItemTap: (id: Int) -> Unit,
    onSearchTap: () -> Unit,
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colors.primary,
            ),
            title = {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = stringResource(R.string.app_name),
                    textAlign = TextAlign.Center,
                    color = whiteColor
                )
            },
            actions = {
                IconButton(onClick = {
                    onSearchTap()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "",
                        tint = whiteColor
                    )
                }
            },
        )

    }) {
        Body(movieListState, onMovieItemTap = onMovieItemTap)
    }
}

@Composable
private fun Body(
    movieListState: LazyPagingItems<MovieDomainModel>, onMovieItemTap: (id: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(movieListState.itemCount) { index ->
            val it = movieListState[index]!!

            Card(
                backgroundColor = movieCardColor,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        onMovieItemTap(it.id)
                    },
            ) {
                Row {
                    AsyncImage(
                        model = Constants.movieImagePath + it.posterPath,
                        contentDescription = "${it.title} Poster",
                        modifier = Modifier.height(200.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 10.dp),
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            it.title, fontSize = 18.sp, fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            it.voteAverage.toString(),
                            fontSize = 16.sp,
                        )
                    }
                }

            }
        }
        movieListState.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Row() {
                            PageLoader(modifier = Modifier.fillMaxSize())
                        }
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = movieListState.loadState.refresh as LoadState.Error
                    item {
                        ErrorMessage(modifier = Modifier.fillMaxSize(),
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingNextPageItem(modifier = Modifier) }
                }

                loadState.append is LoadState.Error -> {
                    val error = movieListState.loadState.append as LoadState.Error
                    item {
                        ErrorMessage(modifier = Modifier,
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }
            }
        }
    }
}


