package com.ashish.moowii.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun PageLoader(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Fetching",
                color = MaterialTheme.colors.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            CircularProgressIndicator(Modifier.padding(top = 10.dp))
        }
    }
}