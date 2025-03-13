package com.mantisbayne.mysteamprofile.ui.viewmodel

data class GameListViewState(
    val emptyStateMessage: String? = null,
    val errorMessage: String? = null,
    val loading: Boolean = false,
    val games: List<GameUiModel> = emptyList()
)
