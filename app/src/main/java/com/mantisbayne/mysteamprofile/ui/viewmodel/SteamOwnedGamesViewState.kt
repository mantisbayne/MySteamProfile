package com.mantisbayne.mysteamprofile.ui.viewmodel

import com.mantisbayne.mysteamprofile.data.model.Game

sealed class SteamOwnedGamesViewState {
    data object Empty : SteamOwnedGamesViewState()
    data object Loading : SteamOwnedGamesViewState()
    data class Error(val errorMessage: String) : SteamOwnedGamesViewState()
    data class Success(val games: List<Game>) : SteamOwnedGamesViewState()
}
