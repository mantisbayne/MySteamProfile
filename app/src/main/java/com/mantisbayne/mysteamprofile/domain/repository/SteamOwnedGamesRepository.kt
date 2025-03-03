package com.mantisbayne.mysteamprofile.domain.repository

import com.mantisbayne.mysteamprofile.data.api.Api
import com.mantisbayne.mysteamprofile.data.model.Game

class SteamOwnedGamesRepository {

    suspend fun getOwnedGames(steamId: String) =
        try {
            val response = Api.steamUserDataService.getOwnedGames(steamId)
            Result.Success(response.games)
        } catch (e: Exception) {
            Result.Error(e)
        }

    sealed class Result<T> {
        data class Error(val error: Throwable? = null) : Result<Error>()
        data class Success(val games: List<Game>?) : Result<Success>()
    }
}
