package com.mantisbayne.mysteamprofile.ui.last2weeks.viewModel

data class Last2WeeksViewState(
    val loading: Boolean = false,
    val emptyStateMessage: String? = null,
    val errorMessage: String? = null,
    val gameTitles: List<String> = emptyList()
)
