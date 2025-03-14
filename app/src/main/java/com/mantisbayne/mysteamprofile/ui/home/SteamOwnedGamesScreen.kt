package com.mantisbayne.mysteamprofile.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mantisbayne.mysteamprofile.R
import com.mantisbayne.mysteamprofile.ui.theme.AppTypography
import com.mantisbayne.mysteamprofile.ui.theme.MySteamProfileTheme
import com.mantisbayne.mysteamprofile.ui.viewmodel.GameUiModel
import com.mantisbayne.mysteamprofile.ui.viewmodel.SteamOwnedGamesViewModel

@Composable
fun SteamOwnedGamesScreen(viewModel: SteamOwnedGamesViewModel) {

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    Surface(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.tertiary) {
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
    LazyColumn(modifier.background(MaterialTheme.colorScheme.onTertiary)) {
        items(games) { game ->
            Card(
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(64.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary)
            ) {
                Row(
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GameIcon(gameUiModel = game)
                    Text(
                        text = game.title,
                        style = AppTypography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = game.playtime,
                        style = AppTypography.labelSmall,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
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
        modifier = Modifier.padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun SteamOwnedGamesScreenPreview() {
//    MySteamProfileTheme {
//        SteamOwnedGamesScreen(viewModel)
//    }
}

@Preview(showBackground = true)
@Composable
fun EmptyStateMessagePreview() {
    MySteamProfileTheme {
        EmptyStateMessage()
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingIndicatorPreview() {
    MySteamProfileTheme {
        LoadingIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun OwnedGamesListPreview() {
    val fakeGames = listOf(
        GameUiModel(title = "Game 1", playtime = "10 hours", imageUrl = "https://via.placeholder.com/150"),
        GameUiModel(title = "Game 2", playtime = "20 hours", imageUrl = "https://via.placeholder.com/150")
    )
    MySteamProfileTheme {
        OwnedGamesList(games = fakeGames)
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorMessagePreview() {
    MySteamProfileTheme {
        ErrorMessage("An error occurred")
    }
}

@Preview(showBackground = true)
@Composable
fun GameIconPreview() {
    val fakeGameUiModel = GameUiModel(title = "Test Game", playtime = "5 hours", imageUrl = "https://via.placeholder.com/150")
    MySteamProfileTheme {
        GameIcon(gameUiModel = fakeGameUiModel)
    }
}
