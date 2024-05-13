package com.project.controlteam.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.project.controlteam.navigation.constants_graph_root.Graph
import com.project.controlteam.screens.hometeam.HomeTeam
import com.project.controlteam.screens.manageteam.ManageTeamScreen
import com.project.controlteam.screens.players.PlayersListScreen

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    teamId: Int?,
    modifier: Modifier
) {
    Scaffold {
        NavHost(
            navController = navController,
            startDestination = Graph.HOME_TEAM,
            route = Graph.HOME_TEAM_NAV,
            modifier = Modifier.padding(it)
        ) {
            composable(route = Graph.HOME_TEAM) { HomeTeam(teamId = teamId) }
            composable(route = Graph.PLAYERS) { PlayersListScreen() }
            composable(route = Graph.MANAGE_TEAM) { ManageTeamScreen() }
        }
    }
}