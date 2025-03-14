package com.mantisbayne.mysteamprofile.ui.reducer

import com.mantisbayne.mysteamprofile.data.model.Game
import com.mantisbayne.mysteamprofile.ui.viewmodel.GameListViewState
import com.mantisbayne.mysteamprofile.ui.viewmodel.GameUiModel
import com.mantisbayne.mysteamprofile.ui.viewmodel.OwnedGamesResult

class GameListViewStateReducer {

    fun getViewState(result: OwnedGamesResult): GameListViewState = when (result) {
        OwnedGamesResult.Empty -> GameListViewState("Empty")
        is OwnedGamesResult.Error -> GameListViewState(errorMessage = result.errorMessage)
        OwnedGamesResult.Loading -> GameListViewState(loading = true)
        is OwnedGamesResult.Success -> GameListViewState(games = mapGames(result.games))
    }

    private fun mapGames(games: List<Game>) =
        games.map { game ->
            GameUiModel(
                game.name,
                mapPlaytime(game.playtime_forever),
                game.getIconUrl()
            )
        }

    private fun mapPlaytime(playtimeForever: Int): String {
        val hours = playtimeForever / 60
        return if (hours == 1) {
            "$hours hour"
        } else {
            "$hours hours"
        }
    }
}