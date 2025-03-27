package com.mantisbayne.mysteamprofile.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mantisbayne.mysteamprofile.domain.usecase.GetOwnedGamesUseCase
import com.mantisbayne.mysteamprofile.ui.intent.GameListIntent
import com.mantisbayne.mysteamprofile.ui.intent.GameSortType
import com.mantisbayne.mysteamprofile.ui.reducer.GameListViewStateReducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
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

    private val _intent = MutableSharedFlow<GameListIntent>(replay = 0, extraBufferCapacity = 1)
    val intent: SharedFlow<GameListIntent> = _intent.asSharedFlow()

    init {
        processIntents()
        sendIntent(GameListIntent.Load)
    }

    fun sendIntent(intent: GameListIntent) {
        _intent.tryEmit(intent)
    }

    private fun processIntents() {
        viewModelScope.launch {
            intent.collectLatest { intent ->
                when (intent) {
                    GameListIntent.Load -> loadGames()
                    is GameListIntent.ClickGame -> handleClick()
                    is GameListIntent.SortGames -> updateViewStateForSort(intent.sortType)
                }
            }
        }
    }

    private fun updateViewStateForSort(sortType: GameSortType) {
        _viewState.update { currentState ->
            currentState.copy(games = sortGames(currentState.games, sortType))
        }
    }

    private fun sortGames(games: List<GameUiModel>, sortType: GameSortType): List<GameUiModel> {
        return when (sortType) {
            GameSortType.ABC_ASC -> games.sortedBy { it.title }
            else -> games.sortedByDescending { it.title }
        }
    }

    private fun handleClick() {
        Log.d("Clicked:", "game")
    }

    private fun loadGames() {
        viewModelScope.launch {
            useCase.getOwnedGames().collect { result ->
                _viewState.update { reducer.getViewState(result) }
            }
        }
    }
}
