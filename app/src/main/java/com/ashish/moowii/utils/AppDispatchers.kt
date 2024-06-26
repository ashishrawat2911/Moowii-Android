package com.ashish.moowii.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

data class AppDispatchers(
    val IO: CoroutineDispatcher = Dispatchers.IO,
    val Main: CoroutineDispatcher = Dispatchers.Main
)