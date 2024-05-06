package com.ashish.moowii.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.ashish.moowii.presentation.navigation.AppNavHost
import com.ashish.moowii.presentation.theme.MoowiiAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoowiiAndroidTheme {
                AppNavHost(navController = rememberNavController())
            }
        }
    }
}
