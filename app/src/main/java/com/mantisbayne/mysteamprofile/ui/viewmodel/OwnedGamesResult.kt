package com.mantisbayne.mysteamprofile.ui.viewmodel

import com.mantisbayne.mysteamprofile.data.model.Game

sealed class OwnedGamesResult {
    data object Empty : OwnedGamesResult()
    data object Loading : OwnedGamesResult()
    data class Error(val errorMessage: String) : OwnedGamesResult()
    data class Success(val games: List<Game>) : OwnedGamesResult()
}
