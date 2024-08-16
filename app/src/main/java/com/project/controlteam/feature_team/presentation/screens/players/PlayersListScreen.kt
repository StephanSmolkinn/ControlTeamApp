package com.project.controlteam.feature_team.presentation.screens.players

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.project.controlteam.feature_team.viewmodel.events.PlayerEvent
import com.project.controlteam.feature_team.viewmodel.states.PlayerState
import com.project.controlteam.feature_team.viewmodel.states.TeamState
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlayersListScreen(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    stateTeam: TeamState,
    state: PlayerState,
    onEvent: (PlayerEvent) -> Unit
) {
    if (state.players.isEmpty()) {
        EmptyPlayersList()
    }
    else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            stickyHeader {
                Surface(
                    modifier = Modifier.fillParentMaxWidth()
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Spacer(modifier = Modifier.height(32.dp))
                        Text(
                            text = "Players",
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                    }
                }
            }
            items(items = state.players, key = { it.id }) {
                Player(
                    navController = navController,
                    coroutineScope = coroutineScope,
                    snackbarHostState = snackbarHostState,
                    player = it,
                    onEvent = onEvent,
                )
            }
        }
    }
}
