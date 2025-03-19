package com.mantisbayne.mysteamprofile.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mantisbayne.mysteamprofile.R
import com.mantisbayne.mysteamprofile.navigation.GameDetailsArgs
import com.mantisbayne.mysteamprofile.ui.theme.AppTypography
import com.mantisbayne.mysteamprofile.ui.theme.MySteamProfileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailsScreen(
    navArgs: GameDetailsArgs,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(navigationIcon = {
                IconButton(onClick = { onBackClick }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }
            },
                title = {  },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onTertiary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onTertiary,
                    actionIconContentColor = MaterialTheme.colorScheme.onTertiary
                )
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.tertiary
        ) {
            Column(
                modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top
            ) {
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .background(MaterialTheme.colorScheme.onTertiaryContainer),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(navArgs.imageUrl)
                                .crossfade(true)
                                .build(),
                            error = painterResource(id = R.drawable.fallback_image),
                            placeholder = painterResource(id = R.drawable.fallback_image),
                            contentDescription = "Game icon",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(96.dp)
                        )
                        Text(
                            text = navArgs.title,
                            style = AppTypography.headlineMedium,
                            modifier = Modifier.padding(bottom = 16.dp),
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(text = navArgs.playtime, style = AppTypography.titleLarge)
                            Text(
                                text = navArgs.lastPlayed,
                                style = AppTypography.titleMedium,
                                modifier = Modifier
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameDetailsScreenPreview() {
    val navArgs = GameDetailsArgs(
        title = "Left 4 Dead 2",
        playtime = "263 hours",
        imageUrl = "https://cdn.akamai.steamstatic.com/steam/apps/550/header.jpg",
        lastPlayed = "Mar 3 2024"
    )
    MySteamProfileTheme {
        GameDetailsScreen(onBackClick = {}, navArgs = navArgs)
    }
}
