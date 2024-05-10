package com.project.controlteam.screens.teams

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
import com.project.controlteam.viewmodel.TeamEvent
import com.project.controlteam.viewmodel.TeamState
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TeamList(
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    navHostController: NavHostController,
    state: TeamState,
    onEvent: (TeamEvent) -> Unit
) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        stickyHeader {
            Surface(modifier = Modifier.fillParentMaxWidth()) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Teams",
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
        items(state.teams, key = { it.id }) {
            Team(
                team = it,
                snackbarHostState = snackbarHostState,
                coroutineScope = coroutineScope,
                navHostController = navHostController,
                state = state,
                onEvent = onEvent
            )
        }
    }

}
