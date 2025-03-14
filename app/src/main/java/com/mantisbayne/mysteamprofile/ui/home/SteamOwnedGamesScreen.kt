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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mantisbayne.mysteamprofile.R
import com.mantisbayne.mysteamprofile.ui.theme.AppTypography
import com.mantisbayne.mysteamprofile.ui.viewmodel.GameUiModel
import com.mantisbayne.mysteamprofile.ui.viewmodel.SteamOwnedGamesViewModel

@Composable
fun SteamOwnedGamesScreen(viewModel: SteamOwnedGamesViewModel) {

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    Surface(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.primary) {
        Box(
            modifier = Modifier
                .statusBarsPadding()
                .padding(16.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            when {
                !viewState.emptyStateMessage.isNullOrEmpty() -> EmptyStateMessage()
                viewState.loading -> LoadingIndicator()
                !viewState.errorMessage.isNullOrEmpty() -> {
                    ErrorMessage(viewState.errorMessage)
                }
                else -> OwnedGamesList(
                    games = viewState.games
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
fun OwnedGamesList(games: List<GameUiModel>, modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
        items(games) { game ->
            Row(
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = game.title,
                    style = AppTypography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                GameIcon(gameUiModel = game)
                Text(
                    text = game.playtime,
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

@Composable
fun GameIcon(gameUiModel: GameUiModel) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(gameUiModel.imageUrl)
            .crossfade(true)
            .build(),
        error = painterResource(id = R.drawable.fallback_image),
        placeholder = painterResource(id = R.drawable.fallback_image),
        contentDescription = "Game icon",
    )
}
