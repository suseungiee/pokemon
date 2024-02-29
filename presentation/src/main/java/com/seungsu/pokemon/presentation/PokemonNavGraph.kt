package com.seungsu.pokemon.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.seungsu.pokemon.presentation.Destinations.DETAIL
import com.seungsu.pokemon.presentation.Destinations.HOME
import com.seungsu.pokemon.presentation.detail.DetailScreen
import com.seungsu.pokemon.presentation.home.HomeScreen

object Destinations {
    const val HOME = "home"
    const val DETAIL = "detail/{pokemon_name}"
}

@Composable
fun PokemonNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = HOME
    ) {
        composable(HOME) {
            HomeScreen(
                onNavigateToDetail = {
                    navController.navigate("detail/$it") {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = DETAIL,
            arguments = listOf(
                navArgument("pokemon_name") {
                    type = NavType.StringType
                }
            )
        ) {
            DetailScreen(
                onPopback = { navController.navigateUp() }
            )
        }
    }
}
