package com.arturomarmolejo.amiiboappamcompose.views.navgraph

sealed class Routes(val route: String) {
    object HomeScreen: Routes("homeScreen")
    object DetailsScreen: Routes("detailsScreen")
}
