package com.mtuci.akino

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mtuci.akino.details.DetailsScreen
import com.mtuci.akino.main.MainScreen
import com.mtuci.akino.ui.theme.AKinoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AKinoTheme {
                val navController = rememberNavController()
                NavHost(
                    startDestination = "main",
                    navController = navController
                ){
                    composable("main"){
                        MainScreen(navController = navController)
                    }
                    composable("details/{id}",
                        arguments = listOf(
                            navArgument(name = "id"){
                                type = NavType.IntType
                            }
                        )
                    ){
                        val id = it.arguments?.getInt("id") ?: error("Please specify `id` when route DetailsScreen")
                        DetailsScreen(
                            navController = navController,
                            id = id
                        )
                    }
                }
            }
        }
    }
}