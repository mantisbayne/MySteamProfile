package com.mantisbayne.mysteamprofile.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mantisbayne.mysteamprofile.navigation.GameDetailsArgs
import com.mantisbayne.mysteamprofile.ui.home.GameIcon
import com.mantisbayne.mysteamprofile.ui.theme.AppTypography

@Composable
fun GameDetailsScreen(
    navArgs: GameDetailsArgs,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.tertiary
    ) {
        Box(
            modifier = Modifier
                .statusBarsPadding()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier) {
                Text(text = navArgs.title, style = AppTypography.headlineMedium)
                Text(text = navArgs.playtime, style = AppTypography.headlineMedium)
                Text(text = navArgs.lastPlayed, style = AppTypography.headlineMedium)
            }
        }
    }
}