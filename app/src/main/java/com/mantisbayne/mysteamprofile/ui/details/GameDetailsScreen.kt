package com.mantisbayne.mysteamprofile.ui.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mantisbayne.mysteamprofile.navigation.GameDetailsArgs

@Composable
fun GameDetailsScreen(
    navArgs: GameDetailsArgs,
    modifier: Modifier = Modifier
) {
    Text(text = "Details")
}