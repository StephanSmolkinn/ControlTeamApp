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
import androidx.navigation.navArgument
import com.project.controlteam.feature_team.presentation.common_components.fabs.FabPlayer
import com.project.controlteam.navigation.constants_graph_root.ArgumentKey
import com.project.controlteam.navigation.constants_graph_root.Graph
import com.project.controlteam.feature_team.presentation.screens.addition_screens.AddPlayerScreen
import com.project.controlteam.feature_team.presentation.screens.finance.FinanceScreen
import com.project.controlteam.feature_team.presentation.screens.hometeam.HomeTeam
import com.project.controlteam.feature_team.presentation.screens.manageteam.ManageTeamScreen
import com.project.controlteam.feature_team.presentation.screens.players.PlayersListScreen
import com.project.controlteam.feature_team.presentation.screens.players.SinglePlayerView
import com.project.controlteam.feature_training_plan.presentation.screens.training_plans.TrainingPlan
import com.project.controlteam.feature_training_plan.presentation.screens.training_plans.TrainingPlansScreen
import com.project.controlteam.ui.navigationbar.NavBar
import com.project.controlteam.utils.PlayerStateTeamId
import com.project.controlteam.feature_team.viewmodel.PlayerViewModel
import com.project.controlteam.feature_training_plan.viewmodel.TrainingPlanViewModel
import com.project.controlteam.feature_team.viewmodel.events.PlayerEvent
import com.project.controlteam.feature_team.viewmodel.events.TeamEvent
import com.project.controlteam.feature_team.viewmodel.states.TeamState

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    modifier: Modifier,
    stateTeam: TeamState,
    onEventTeam: (TeamEvent) -> Unit,
    playerViewModel: PlayerViewModel = hiltViewModel(),
    trainingPlanViewModel: TrainingPlanViewModel = hiltViewModel()
) {

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    val coroutineScope = rememberCoroutineScope()

    val statePlayer by playerViewModel.statePlayer.collectAsState()
    val stateTrainingPlan by trainingPlanViewModel.stateTrainingPlan.collectAsState()

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
        },
        modifier = modifier
    ) {
        NavHost(
            navController = navController,
            startDestination = Graph.HOME_TEAM,
            route = Graph.HOME_TEAM_NAV,
            modifier = Modifier.padding(it)
        ) {
            composable(route = Graph.HOME_TEAM) {
                playerViewModel.onPlayerEvent(PlayerEvent.GetSumOfSalary(PlayerStateTeamId.teamId))
                onEventTeam(TeamEvent.GetFinanceTeam(PlayerStateTeamId.teamId))
                HomeTeam(
                    stateTeam = stateTeam,
                    onEventTeam = onEventTeam,
                    statePlayer = statePlayer,
                    onEventPlayer = playerViewModel::onPlayerEvent
                )
            }
            composable(route = Graph.PLAYERS) {
                PlayersListScreen(
                    navController = navController,
                    coroutineScope = coroutineScope,
                    stateTeam = stateTeam,
                    state = statePlayer,
                    onEvent = playerViewModel::onPlayerEvent,
                    snackbarHostState = snackBarHostState
                )
            }
            composable(route = Graph.MANAGE_TEAM) {
                ManageTeamScreen(
                    navController = navController,
                    state = statePlayer,
                    onEvent = playerViewModel::onPlayerEvent,
                    stateTeam = stateTeam,
                    onEventTeam = onEventTeam
                )
            }
            composable(route = Graph.ADD_PLAYER) {
                AddPlayerScreen(
                    navController = navController,
                    state = statePlayer,
                    onEvent = playerViewModel::onPlayerEvent
                )
            }
            composable(
                route = Graph.SINGLE_PLAYER_VIEW + "/{${ArgumentKey.PLAYER_ARGUMENT_KEY}}",
                arguments = listOf(
                    navArgument(ArgumentKey.PLAYER_ARGUMENT_KEY) {
                        type = NavType.IntType
                    }
                )
            ) {
                SinglePlayerView(
                    playerId = it.arguments?.getInt(ArgumentKey.PLAYER_ARGUMENT_KEY),
                    navController = navController,
                    state = statePlayer,
                    onEvent = playerViewModel::onPlayerEvent
                )
            }
            composable(route = Graph.TRAINING_PLAN) {
                TrainingPlansScreen(
                    navController = navController,
                    stateTrainingPlan = stateTrainingPlan,
                    onEvent = trainingPlanViewModel::onTrainingPlanEvent
                )
            }
            composable(route = Graph.ADD_TRAINING_PLAN) {
                TrainingPlan(
                    navController = navController,
                    stateTrainingPlan = stateTrainingPlan,
                    onEvent = trainingPlanViewModel::onTrainingPlanEvent
                )
            }
            composable(route = Graph.FINANCE) {
                FinanceScreen(
                    navController = navController,
                    state = statePlayer,
                    stateTeam = stateTeam,
                    onEventTeam = onEventTeam
                )
            }
        }
    }
}