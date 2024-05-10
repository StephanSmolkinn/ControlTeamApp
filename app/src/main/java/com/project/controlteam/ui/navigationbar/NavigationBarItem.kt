package com.project.controlteam.ui.navigationbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.project.controlteam.navigation.constants_graph_root.Graph

sealed class NavigationBarItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object HomeTeam : NavigationBarItem(
        route = Graph.HOME_TEAM,
        title = "Home",
        icon = Icons.Default.Home
    )

    object Players : NavigationBarItem(
        route = Graph.PLAYERS,
        title = "Players",
        icon = Icons.Default.Person
    )

    object ManageTeam : NavigationBarItem(
        route = Graph.MANAGE_TEAM,
        title = "Manage",
        icon = Icons.Default.Build
    )
}