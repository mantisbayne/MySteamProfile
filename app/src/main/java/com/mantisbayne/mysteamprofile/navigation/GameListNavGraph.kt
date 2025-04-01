package com.mantisbayne.mysteamprofile.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mantisbayne.mysteamprofile.ui.details.GameDetailsScreen
import com.mantisbayne.mysteamprofile.ui.home.SteamOwnedGamesScreen
import com.mantisbayne.mysteamprofile.ui.last2weeks.Last2WeeksScreen

@Composable
fun GameNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "game_list") {
        composable("game_list") {
            SteamOwnedGamesScreen(viewModel = hiltViewModel(), navController)
        }

        composable("game_details") {
            val args = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<GameDetailsArgs>("gameArgs")

            args?.let {
                GameDetailsScreen(it, {})
            } ?: Text(text = "Error: No game details provided")
        }

        composable("game_2weeks") {
            Last2WeeksScreen(viewModel = hiltViewModel(), navController)
        }
    }
}
