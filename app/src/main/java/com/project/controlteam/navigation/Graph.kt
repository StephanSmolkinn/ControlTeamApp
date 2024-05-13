package com.project.controlteam.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.controlteam.navigation.constants_graph_root.Graph
import com.project.controlteam.screens.additionteam.AddTeamScreen
import com.project.controlteam.screens.hometeam.HomeTeamScreen
import com.project.controlteam.screens.teams.TeamListScreen
import com.project.controlteam.ui.navigationbar.HOME_TEAM_ARGUMENT_KEY
import com.project.controlteam.viewmodel.TeamEvent
import com.project.controlteam.viewmodel.TeamState

@Composable
fun Graph(
    navHostController: NavHostController = rememberNavController(),
    state: TeamState,
    onEvent: (TeamEvent) -> Unit
) {

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
                Fab(navController = navHostController)
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
                    state = state,
                    onEvent = onEvent
                )
            }
            composable(route = Graph.ADD_TEAM) {
                AddTeamScreen(
                    navController = navHostController,
                    state = state,
                    onEvent = onEvent
                )
            }
            composable(
                route = Graph.HOME_TEAM_NAV + "/{$HOME_TEAM_ARGUMENT_KEY}",
                arguments = listOf(
                    navArgument(HOME_TEAM_ARGUMENT_KEY) {
                        type = NavType.IntType
                    }
                )
            ) {
                HomeTeamScreen(
                    teamId = it.arguments?.getInt(HOME_TEAM_ARGUMENT_KEY)
                )
            }
        }
    }
}