package com.mantisbayne.mysteamprofile.domain.usecase

import com.mantisbayne.mysteamprofile.data.model.Game
import com.mantisbayne.mysteamprofile.domain.repository.SteamOwnedGamesRepository
import com.mantisbayne.mysteamprofile.ui.viewmodel.OwnedGamesResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetOwnedGamesUseCase(private val repository: SteamOwnedGamesRepository) {

    fun getOwnedGames(): Flow<OwnedGamesResult> = flow {
        emit(OwnedGamesResult.Loading)
        // hardcode for test user
        when (val result = repository.getOwnedGames("76561198397381690")) {
            SteamOwnedGamesRepository.Result.Empty -> emit(OwnedGamesResult.Empty)
            is SteamOwnedGamesRepository.Result.Error -> emit(handleError(result.error?.message))
            is SteamOwnedGamesRepository.Result.Success -> emit(handleSuccess(result.games))
        }
    }

    private fun handleError(message: String?) =
        OwnedGamesResult.Error(message ?: "An unknown error occurred")

    private fun handleSuccess(result: List<Game>?) =
        if (result.isNullOrEmpty()) {
            OwnedGamesResult.Empty
        } else {
            OwnedGamesResult.Success(result)
        }
}
