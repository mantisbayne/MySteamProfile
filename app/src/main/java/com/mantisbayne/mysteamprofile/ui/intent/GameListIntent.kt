package com.mantisbayne.mysteamprofile.ui.intent

import com.mantisbayne.mysteamprofile.navigation.GameDetailsArgs

sealed class GameListIntent {
    data object Load : GameListIntent()
    data class ClickGame(val gameDetailsArgs: GameDetailsArgs) : GameListIntent()
    data class SortGames(val sortType: GameSortType) : GameListIntent()
}

enum class GameSortType {
    ABC_ASC,
    ABC_DESC,
    PLAYTIME_ASC,
    PLAYTIME_DESC,
    LAST_PLAYED_ASC,
    LAST_PLAYED_DESC
}
