package com.mantisbayne.mysteamprofile.ui.reducer

import android.annotation.SuppressLint
import com.mantisbayne.mysteamprofile.data.model.Game
import com.mantisbayne.mysteamprofile.ui.viewmodel.GameListViewState
import com.mantisbayne.mysteamprofile.ui.viewmodel.GameUiModel
import com.mantisbayne.mysteamprofile.ui.viewmodel.OwnedGamesResult
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

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
                game.getIconUrl(),
                mapLastPlayedDate(game.playtime_forever, game.rtime_last_played)
            )
        }

    @SuppressLint("NewApi")
    private fun mapLastPlayedDate(playtimeForever: Int, rtimeLastPlayed: Long): String {
        return if (playtimeForever == 0) {
            "Never played"
        } else {
            val instant = Instant.ofEpochMilli(rtimeLastPlayed)
            val timezone = instant.atZone(ZoneId.systemDefault())
            val formatter = DateTimeFormatter.ofPattern("MMM dd yy", Locale.getDefault())
            timezone.format(formatter)
        }
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