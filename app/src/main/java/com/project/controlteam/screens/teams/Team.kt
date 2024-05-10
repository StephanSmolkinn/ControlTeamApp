package com.project.controlteam.screens.teams

import androidx.compose.animation.scaleIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.project.controlteam.data.model.Team
import com.project.controlteam.navigation.constants_graph_root.Graph
import com.project.controlteam.viewmodel.TeamListEvent
import com.project.controlteam.viewmodel.TeamListState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Team(
    team: Team,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    navHostController: NavHostController,
    state: TeamListState,
    onEvent: (TeamListEvent) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { navHostController.navigate(Graph.HOME_TEAM) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = team.teamTitle,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(6.dp)
                )
                Text(
                    text = "Sport - ${team.teamSport}",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(6.dp)
                )
            }

            IconButton(
                onClick = {
                    onEvent(TeamListEvent.DeleteTeam(team))
                    coroutineScope.launch {
                        val snackBar = snackbarHostState.showSnackbar(
                            message = "The team has been deleted",
                            actionLabel = "Undo",
                            duration = SnackbarDuration.Short
                        )

                        when(snackBar) {
                            SnackbarResult.ActionPerformed -> {
                                onEvent(TeamListEvent.UnDeleteTeam(team))
                            }

                            else -> {  }
                        }
                    }

                },
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }

        }
    }
}