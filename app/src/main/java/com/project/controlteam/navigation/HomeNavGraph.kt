package com.project.controlteam.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.project.controlteam.navigation.constants_graph_root.Graph
import com.project.controlteam.screens.addition_screens.AddPlayerScreen
import com.project.controlteam.screens.fabs.FabPlayer
import com.project.controlteam.screens.hometeam.HomeTeam
import com.project.controlteam.screens.manageteam.ManageTeamScreen
import com.project.controlteam.screens.players.PlayersListScreen
import com.project.controlteam.ui.navigationbar.NavBar
import com.project.controlteam.viewmodel.PlayerViewModel
import com.project.controlteam.viewmodel.events.TeamEvent
import com.project.controlteam.viewmodel.events.states.TeamState

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    teamId: Int?,
    modifier: Modifier,
    stateTeam: TeamState,
    onEventTeam: (TeamEvent) -> Unit,
    playerViewModel: PlayerViewModel = hiltViewModel()
) {

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    val coroutineScope = rememberCoroutineScope()

    val statePlayer by playerViewModel.statePlayer.collectAsState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val destination = navBackStackEntry?.destination?.route

    val fabShow = destination?.let {
        it in listOf(Graph.PLAYERS)
    } ?: false

    val showNavBar = destination?.let {
        it in listOf(Graph.HOME_TEAM, Graph.PLAYERS, Graph.MANAGE_TEAM)
    } ?: false

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        floatingActionButton = {
            if (fabShow) {
                FabPlayer(
                    navController = navController,
                    state = statePlayer,
                    onEvent = playerViewModel::onPlayerEvent
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            if (showNavBar)
                NavBar(navController = navController)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Graph.HOME_TEAM,
            route = Graph.HOME_TEAM_NAV,
            modifier = Modifier.padding(it)
        ) {
            composable(route = Graph.HOME_TEAM) {
                HomeTeam(
                    teamId = teamId,
                    state = stateTeam,
                    onEvent = playerViewModel::onPlayerEvent
                )
            }
            composable(route = Graph.PLAYERS) {
                PlayersListScreen(
                    coroutineScope = coroutineScope,
                    stateTeam = stateTeam,
                    state = statePlayer,
                    onEvent = playerViewModel::onPlayerEvent,
                    snackbarHostState = snackBarHostState
                )
            }
            composable(route = Graph.MANAGE_TEAM) { ManageTeamScreen() }
            composable(route = Graph.ADD_PLAYER) {
                AddPlayerScreen(
                    navController = navController,
                    state = statePlayer,
                    onEvent = playerViewModel::onPlayerEvent
                )
            }
        }
    }
}