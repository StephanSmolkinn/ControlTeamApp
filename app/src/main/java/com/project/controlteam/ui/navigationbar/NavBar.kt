package com.project.controlteam.ui.navigationbar

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavBar(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = Color.Transparent,
        contentColor = Color.Blue
    ) {
       ListBarItem.listItem.forEach {
            this.AddItem(
                item = it,
                navController = navController,
                currentDestination = currentDestination,
            )
        }
    }
}