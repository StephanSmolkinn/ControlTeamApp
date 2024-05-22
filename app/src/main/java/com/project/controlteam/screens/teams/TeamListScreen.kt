package com.project.controlteam.screens.teams

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.project.controlteam.viewmodel.events.TeamEvent
import com.project.controlteam.viewmodel.events.states.TeamState
import kotlinx.coroutines.CoroutineScope

@Composable
fun TeamListScreen(
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    navHostController: NavHostController,
    state: TeamState,
    onEvent: (TeamEvent) -> Unit
) {
    if (state.teams.isEmpty()) EmptyTeamList() else TeamList(
        snackbarHostState = snackbarHostState,
        coroutineScope = coroutineScope,
        navHostController = navHostController,
        state = state,
        onEvent = onEvent
    )
}

@Preview(showSystemUi = true)
@Composable
fun TeamListPreview() {

}