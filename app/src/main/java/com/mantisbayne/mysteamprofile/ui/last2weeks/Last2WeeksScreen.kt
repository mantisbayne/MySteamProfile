package com.mantisbayne.mysteamprofile.ui.last2weeks

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.mantisbayne.mysteamprofile.ui.components.AppSurface
import com.mantisbayne.mysteamprofile.ui.last2weeks.viewModel.Last2WeeksViewModel

@Composable
fun Last2WeeksScreen(
    viewModel: Last2WeeksViewModel,
    navController: NavHostController
) {

   AppSurface {
       Last2WeeksContent(navController, viewModel)
   }
}

@Composable
fun Last2WeeksContent(
    navController: NavHostController,
    viewModel: Last2WeeksViewModel,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {

    }
}
