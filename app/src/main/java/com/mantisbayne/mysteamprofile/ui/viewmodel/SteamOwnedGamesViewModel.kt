package com.mantisbayne.mysteamprofile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mantisbayne.mysteamprofile.domain.usecase.GetOwnedGamesUseCase
import com.mantisbayne.mysteamprofile.ui.reducer.GameListViewStateReducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SteamOwnedGamesViewModel @Inject constructor(
    private val reducer: GameListViewStateReducer,
    private val useCase: GetOwnedGamesUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(GameListViewState(loading = true))
    val viewState: StateFlow<GameListViewState> = _viewState

    init {
        viewModelScope.launch {
            useCase.getOwnedGames().collect { result ->
                _viewState.update { reducer.getViewState(result) }
            }
        }
    }
}