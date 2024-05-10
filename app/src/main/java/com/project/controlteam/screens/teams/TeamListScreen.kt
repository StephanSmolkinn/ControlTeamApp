package com.project.controlteam.screens.teams

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.project.controlteam.data.model.Team
import com.project.controlteam.viewmodel.TeamEvent
import com.project.controlteam.viewmodel.TeamState
import kotlinx.coroutines.CoroutineScope
import java.time.LocalDateTime

@Composable
fun TeamListScreen(
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    navHostController: NavHostController,
    state: TeamState,
    onEvent: (TeamEvent) -> Unit
) {
    val dateTest = LocalDateTime.now()
    val list = remember {
        mutableStateListOf<Team>()
    }


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