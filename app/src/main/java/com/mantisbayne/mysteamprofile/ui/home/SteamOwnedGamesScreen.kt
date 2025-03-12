package com.mantisbayne.mysteamprofile.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mantisbayne.mysteamprofile.data.model.Game
import com.mantisbayne.mysteamprofile.ui.theme.AppTypography
import com.mantisbayne.mysteamprofile.ui.viewmodel.SteamOwnedGamesViewModel
import com.mantisbayne.mysteamprofile.ui.viewmodel.SteamOwnedGamesViewState

@Composable
fun SteamOwnedGamesScreen(viewModel: SteamOwnedGamesViewModel) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getOwnedGames()
    }

    Surface(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.primary) {
        Box(
            modifier = Modifier
                .statusBarsPadding()
                .padding(16.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            when (uiState) {
                SteamOwnedGamesViewState.Empty -> EmptyStateMessage()
                SteamOwnedGamesViewState.Loading -> LoadingIndicator()
                is SteamOwnedGamesViewState.Error -> {
                    val state = uiState as SteamOwnedGamesViewState.Error
                    ErrorMessage(state.errorMessage)
                }

                is SteamOwnedGamesViewState.Success -> OwnedGamesList(
                    games = (uiState as SteamOwnedGamesViewState.Success).games
                )
            }
        }
    }
}

@Composable
fun EmptyStateMessage() {
    Text("So much empty", modifier = Modifier.fillMaxWidth())
}

@Composable
fun LoadingIndicator() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun OwnedGamesList(games: List<Game>, modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
        items(games) { game ->
            Row(
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // TODO: use a reducer to set playtime in ViewState
                // row should be a view object
                val playtimeInHours = game.playtime_forever / 60
                Text(
                    text = game.name,
                    style = AppTypography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "$playtimeInHours hours",
                    textAlign = TextAlign.End,
                    style = AppTypography.labelSmall
                )
            }
        }
    }
}

@Composable
fun ErrorMessage(error: String?) {
    Text(text = error ?: "error message", modifier = Modifier.fillMaxWidth())
}
