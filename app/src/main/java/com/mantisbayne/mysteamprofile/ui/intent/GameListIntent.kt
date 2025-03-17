package com.mantisbayne.mysteamprofile.ui.intent

sealed class GameListIntent {
    data object Load : GameListIntent()
    data class ClickGame(val id: Int) : GameListIntent()
}
