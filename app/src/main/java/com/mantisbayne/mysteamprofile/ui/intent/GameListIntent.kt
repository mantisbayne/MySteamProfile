package com.mantisbayne.mysteamprofile.ui.intent

import com.mantisbayne.mysteamprofile.navigation.GameDetailsArgs

sealed class GameListIntent {
    data object Load : GameListIntent()
    data class ClickGame(val gameDetailsArgs: GameDetailsArgs) : GameListIntent()
}
