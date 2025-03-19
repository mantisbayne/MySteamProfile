package com.mantisbayne.mysteamprofile.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mantisbayne.mysteamprofile.R
import com.mantisbayne.mysteamprofile.navigation.GameDetailsArgs
import com.mantisbayne.mysteamprofile.ui.theme.AppTypography
import com.mantisbayne.mysteamprofile.ui.theme.MySteamProfileTheme
import com.mantisbayne.mysteamprofile.ui.viewmodel.GameUiModel
import com.mantisbayne.mysteamprofile.ui.viewmodel.SteamOwnedGamesViewModel

@Composable
fun SteamOwnedGamesScreen(viewModel: SteamOwnedGamesViewModel, navController: NavController) {

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.tertiary) {
        Box(
            modifier = Modifier
                .statusBarsPadding()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            when {
                !viewState.emptyStateMessage.isNullOrEmpty() -> EmptyStateMessage()
                viewState.loading -> LoadingIndicator()
                !viewState.errorMessage.isNullOrEmpty() -> {
                    ErrorMessage(viewState.errorMessage)
                }
                else -> OwnedGamesList(
                    games = viewState.games,
                    navController
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
fun OwnedGamesList(games: List<GameUiModel>, navController: NavController, modifier: Modifier = Modifier) {
    GameListSection(games = games, navController, modifier = modifier)
}

@Composable
fun GameListSection(games: List<GameUiModel>, navController: NavController, modifier: Modifier) {
    LazyColumn(modifier.background(MaterialTheme.colorScheme.tertiary)) {
        items(games) { game ->
            Card(
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(96.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            ) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    GameItemContent(game, navController, modifier)
                }
            }
        }
    }
}

@Composable
fun GameItemContent(game: GameUiModel, navController: NavController, modifier: Modifier) {
    val args = GameDetailsArgs(
        game.title,
        game.playtime,
        game.imageUrl,
        game.lastPlayedDate
    )

    Row(
        Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("gameArgs", args)

                navController.navigate("game_details")
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        GameIcon(gameUiModel = game)
        Text(
            text = game.title,
            style = AppTypography.titleLarge,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        )
        PlaytimeInfo(game = game, modifier = modifier)
    }
}

@Composable
fun PlaytimeInfo(modifier: Modifier = Modifier, game: GameUiModel) {
    Column(modifier = modifier.padding(end = 8.dp, start = 8.dp)) {
        Text(
            text = stringResource(id = R.string.total_played),
            style = AppTypography.titleMedium
        )
        Text(
            text = game.playtime,
            style = AppTypography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun ErrorMessage(error: String?) {
    Text(
        text = error ?: stringResource(id = R.string.unknown_error),
        modifier = Modifier.fillMaxWidth()
    )
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
        modifier = Modifier
            .padding(8.dp)
            .size(48.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun SteamOwnedGamesScreenPreview() {
    // TODO: make a preview
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
//    TODO
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

}
