package com.project.controlteam.ui.navigationbar

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavBar(navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavigationBar(
        containerColor = Color.Transparent,
        contentColor = Color.Blue
    ) {
        ListBarItem.listItem.forEach {
            this.AddItem(
                item = it,
                navController = navController,
                currentDestination = navBackStackEntry?.destination
            )
        }
    }
}