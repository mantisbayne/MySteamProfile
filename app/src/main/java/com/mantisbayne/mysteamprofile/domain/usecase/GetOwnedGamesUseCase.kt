package com.mantisbayne.mysteamprofile.domain.usecase

import com.mantisbayne.mysteamprofile.data.model.Game
import com.mantisbayne.mysteamprofile.domain.repository.SteamOwnedGamesRepository
import com.mantisbayne.mysteamprofile.ui.viewmodel.SteamOwnedGamesViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GetOwnedGamesUseCase(private val repository: SteamOwnedGamesRepository) {

    private val _viewState = MutableStateFlow<SteamOwnedGamesViewState>(SteamOwnedGamesViewState.Loading)
    val viewState: StateFlow<SteamOwnedGamesViewState> = _viewState

    suspend fun getOwnedGames() {
        _viewState.value = SteamOwnedGamesViewState.Loading
        // hardcode for test user
        when (val result = repository.getOwnedGames("76561198397381690")) {
            is SteamOwnedGamesRepository.Result.Error -> handleError(result.error?.message)
            is SteamOwnedGamesRepository.Result.Success -> handleSuccess(result.games)
        }
    }

    private fun handleError(message: String?) {
        val error = message ?: "An unknown error occurred"
        _viewState.value = SteamOwnedGamesViewState.Error(error)
    }

    private fun handleSuccess(result: List<Game>?) {
        if (result.isNullOrEmpty()) {
            _viewState.value = SteamOwnedGamesViewState.Empty
        } else {
            _viewState.value = SteamOwnedGamesViewState.Success(result)
        }
    }
}
