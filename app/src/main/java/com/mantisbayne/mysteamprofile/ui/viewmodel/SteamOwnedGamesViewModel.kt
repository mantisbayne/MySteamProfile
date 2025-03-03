package com.mantisbayne.mysteamprofile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mantisbayne.mysteamprofile.domain.usecase.GetOwnedGamesUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SteamOwnedGamesViewModel(private val useCase: GetOwnedGamesUseCase) : ViewModel() {

    val uiState: StateFlow<SteamOwnedGamesViewState> = useCase.viewState

    fun getOwnedGames() {
        viewModelScope.launch {
            useCase.getOwnedGames()
        }
    }
}