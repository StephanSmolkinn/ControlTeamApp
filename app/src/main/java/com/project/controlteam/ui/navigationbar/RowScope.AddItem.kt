package com.project.controlteam.ui.navigationbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController

@Composable
fun RowScope.AddItem(
    item: NavigationBarItem,
    navController: NavHostController,
    currentDestination: NavDestination?,
) {
    NavigationBarItem(
        label = { Text(text = item.title) },
        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
        onClick = {
            navController.navigate(item.route)
        },
        icon = { Icon(imageVector = item.icon, contentDescription = item.title) }
    )
}