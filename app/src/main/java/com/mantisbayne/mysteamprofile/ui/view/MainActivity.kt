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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mantisbayne.mysteamprofile.domain.usecase.GetOwnedGamesUseCase
import com.mantisbayne.mysteamprofile.ui.theme.MySteamProfileTheme
import com.mantisbayne.mysteamprofile.ui.viewmodel.SteamOwnedGamesViewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<SteamOwnedGamesViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val useCase = GetOwnedGamesUseCase()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MySteamProfileTheme {
                SteamOwnedGamesScreen(viewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MySteamProfileTheme {
        Greeting("Android")
    }
}