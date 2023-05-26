package com.arturomarmolejo.amiiboappamcompose.views.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arturomarmolejo.amiiboappamcompose.viewmodel.AmiiboViewModel
import com.arturomarmolejo.amiiboappamcompose.views.screens.DetailsScreen
import com.arturomarmolejo.amiiboappamcompose.views.screens.HomeScreen

@Composable
fun Navigation(viewModel: AmiiboViewModel) {
    val navigationController = rememberNavController()

    NavHost(
        navController = navigationController,
        startDestination = Routes.HomeScreen.route
    ) {
        composable(Routes.HomeScreen.route) {
            HomeScreen(amiiboViewModel = viewModel, navController = navigationController)
        }
        composable(Routes.DetailsScreen.route) {
            DetailsScreen(viewModel)
        }
    }
}