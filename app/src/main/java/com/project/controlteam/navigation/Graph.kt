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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.controlteam.feature_team.presentation.common_components.FabCustom
import com.project.controlteam.navigation.constants_graph_root.ArgumentKey
import com.project.controlteam.navigation.constants_graph_root.Graph
import com.project.controlteam.feature_team.presentation.screens.addition_screens.AddTeamScreen
import com.project.controlteam.feature_team.presentation.screens.hometeam.HomeTeamScreen
import com.project.controlteam.feature_team.presentation.screens.teams.TeamListScreen
import com.project.controlteam.feature_team.viewmodel.TeamViewModel
import com.project.controlteam.feature_team.viewmodel.events.TeamEvent

@Composable
fun Graph(
    navHostController: NavHostController = rememberNavController(),
    teamViewModel: TeamViewModel = hiltViewModel()
) {
    val teams by teamViewModel.state.collectAsState()

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    val coroutineScope = rememberCoroutineScope()

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val destination = navBackStackEntry?.destination?.route

    val fabShow = destination?.let {
        it in Graph.TEAM_LIST
    } ?: false

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        floatingActionButton = {
            if (fabShow)
                FabCustom(
                    onEvent = {
                        teamViewModel.onTeamEvent(TeamEvent.SetTeamTitle(""))
                        teamViewModel.onTeamEvent(TeamEvent.SetTeamSport(""))
                        navHostController.navigate(Graph.ADD_TEAM)
                    },
                    text = "Add team"
                )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        NavHost(
            navController = navHostController,
            startDestination = Graph.TEAM_LIST,
            modifier = Modifier.padding(it)
        ) {
            composable(route = Graph.TEAM_LIST) {
                TeamListScreen(
                    snackbarHostState = snackBarHostState,
                    coroutineScope = coroutineScope,
                    navHostController = navHostController,
                    state = teams,
                    onEvent = teamViewModel::onTeamEvent
                )
            }
            composable(route = Graph.ADD_TEAM) {
                AddTeamScreen(
                    navController = navHostController,
                    state = teams,
                    onEvent = teamViewModel::onTeamEvent
                )
            }
            composable(
                route = "home_team_nav/{${ArgumentKey.HOME_TEAM_ARGUMENT_KEY}}",
                arguments = listOf(
                    navArgument(ArgumentKey.HOME_TEAM_ARGUMENT_KEY) {
                        type = NavType.IntType
                    }
                )
            ) {
                HomeTeamScreen(
                    stateTeam = teams,
                    teamId = it.arguments?.getInt(ArgumentKey.HOME_TEAM_ARGUMENT_KEY),
                    onEventTeam = teamViewModel::onTeamEvent
                )
            }
        }
    }
}