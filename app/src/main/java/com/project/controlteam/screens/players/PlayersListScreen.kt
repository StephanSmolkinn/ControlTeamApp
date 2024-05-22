package com.project.controlteam.screens.players

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.project.controlteam.viewmodel.events.PlayerEvent
import com.project.controlteam.viewmodel.events.states.PlayerState
import com.project.controlteam.viewmodel.events.states.TeamState
import kotlinx.coroutines.CoroutineScope

@Composable
fun PlayersListScreen(
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    stateTeam: TeamState,
    state: PlayerState,
    onEvent: (PlayerEvent) -> Unit
) {
    if (state.players.isEmpty()) EmptyPlayersList() else PlayerList(
        snackbarHostState = snackbarHostState,
        coroutineScope = coroutineScope,
        state = state,
        onEvent = onEvent
    )
}

@Preview(showSystemUi = true)
@Composable
fun PlayersListScreenPreview() {

}