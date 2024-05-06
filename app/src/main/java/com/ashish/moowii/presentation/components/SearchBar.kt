package com.ashish.moowii.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun CustomSearchBar(onValueChange: (String) -> Unit) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        label = { Text(text = "") },
        value = text,
        shape = RoundedCornerShape(size = 8.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        onValueChange = {
            text = it
            onValueChange(it.text);
        },
    )
}