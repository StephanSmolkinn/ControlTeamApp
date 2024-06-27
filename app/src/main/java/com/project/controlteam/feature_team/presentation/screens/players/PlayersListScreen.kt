package com.project.controlteam.feature_team.presentation.screens.players

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.project.controlteam.feature_team.viewmodel.events.PlayerEvent
import com.project.controlteam.feature_team.viewmodel.states.PlayerState
import com.project.controlteam.feature_team.viewmodel.states.TeamState
import kotlinx.coroutines.CoroutineScope

@Composable
fun PlayersListScreen(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    stateTeam: TeamState,
    state: PlayerState,
    onEvent: (PlayerEvent) -> Unit
) {
    if (state.players.isEmpty()) EmptyPlayersList() else PlayerList(
        navController = navController,
        snackbarHostState = snackbarHostState,
        coroutineScope = coroutineScope,
        state = state,
        onEvent = onEvent
    )
}
