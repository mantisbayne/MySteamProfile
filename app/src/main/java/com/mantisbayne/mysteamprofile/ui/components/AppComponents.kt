package com.mantisbayne.mysteamprofile.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    content: @Composable (PaddingValues) -> Unit,
    navigationIcon: () -> Unit = {},
    title: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(navigationIcon = { navigationIcon() },
                title = { title() },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onTertiary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onTertiary,
                    actionIconContentColor = MaterialTheme.colorScheme.onTertiary
                )
            )
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}

@Composable
fun AppSurface(
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.tertiary
    ) {
        content()
    }
}
