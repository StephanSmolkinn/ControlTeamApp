package com.project.controlteam.ui.navigationbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.project.controlteam.navigation.constants_graph_root.Graph

const val HOME_TEAM_ARGUMENT_KEY = "teamId"

sealed class NavigationBarItem(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
     object HomeTeam : NavigationBarItem(
        route = Graph.HOME_TEAM,
        title = "Home",
        icon = Icons.Filled.Home,
    )

    object Players : NavigationBarItem(
        route = Graph.PLAYERS,
        title = "Players",
        icon = Icons.Filled.Person
    )

    object ManageTeam : NavigationBarItem(
        route = Graph.MANAGE_TEAM,
        title = "Manage",
        icon = Icons.Filled.Build
    )

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}