package com.mantisbayne.mysteamprofile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mantisbayne.mysteamprofile.domain.usecase.GetOwnedGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SteamOwnedGamesViewModel @Inject constructor(private val useCase: GetOwnedGamesUseCase) : ViewModel() {

    val uiState: StateFlow<SteamOwnedGamesViewState> = useCase.viewState

    fun getOwnedGames() {
        viewModelScope.launch {
            useCase.getOwnedGames()
        }
    }
}