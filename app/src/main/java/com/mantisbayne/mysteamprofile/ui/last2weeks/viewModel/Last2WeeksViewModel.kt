package com.mantisbayne.mysteamprofile.ui.last2weeks.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Last2WeeksViewModel @Inject constructor() : ViewModel() {

    private val _viewState = MutableStateFlow(Last2WeeksViewState())
    val viewState: StateFlow<Last2WeeksViewState> = _viewState

    fun getLast2WeeksGames() =
        viewModelScope.launch {

        }
}