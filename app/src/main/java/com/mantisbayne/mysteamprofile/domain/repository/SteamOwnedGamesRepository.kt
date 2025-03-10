package com.mantisbayne.mysteamprofile.domain.repository

import com.mantisbayne.mysteamprofile.data.api.Api
import com.mantisbayne.mysteamprofile.data.model.Game

class SteamOwnedGamesRepository {

    suspend fun getOwnedGames(steamId: String) =
        try {
            val response = Api.steamUserDataService.getOwnedGames(steamId)
            response.response?.let {
                Result.Success(it.games)
            } ?: Result.Empty
        } catch (e: Exception) {
            Result.Error(e)
        }

    sealed class Result<T> {
        data object Empty : Result<Empty>()
        data class Error(val error: Throwable? = null) : Result<Error>()
        data class Success(val games: List<Game>?) : Result<Success>()
    }
}
