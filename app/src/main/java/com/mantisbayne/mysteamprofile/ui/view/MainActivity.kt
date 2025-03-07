package com.mantisbayne.mysteamprofile.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mantisbayne.mysteamprofile.domain.usecase.GetOwnedGamesUseCase
import com.mantisbayne.mysteamprofile.ui.theme.MySteamProfileTheme
import com.mantisbayne.mysteamprofile.ui.viewmodel.SteamOwnedGamesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: SteamOwnedGamesViewModel = hiltViewModel()
            MySteamProfileTheme {
                SteamOwnedGamesScreen(viewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SteamOwnedGamesPreview() {
    MySteamProfileTheme {
    }
}