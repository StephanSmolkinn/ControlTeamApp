package com.project.controlteam.feature_team.presentation.screens.players

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
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
import com.project.controlteam.feature_team.data.model.Player
import com.project.controlteam.navigation.constants_graph_root.Graph
import com.project.controlteam.feature_team.viewmodel.events.PlayerEvent
import com.project.controlteam.feature_team.viewmodel.events.TeamEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Player(
    navController: NavHostController,
    coroutineScope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    player: Player,
    onEvent: (PlayerEvent) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
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
                    text = player.playerName,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textAlign = TextAlign.Justify,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(6.dp)
                )
                Text(
                    text = "Position ${player.playerPosition}",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(6.dp)
                )
            }

            IconButton(
                onClick = {
                    onEvent(PlayerEvent.GetOnePlayer(player.id))
                    navController.navigate(Graph.SINGLE_PLAYER_VIEW + "/${player.id}")
                },
                modifier = Modifier.padding(4.dp)
            ) {
                Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null)
            }

            IconButton(
                onClick = {
                    onEvent(PlayerEvent.DeletePlayer(player))
                    coroutineScope.launch {
                        val snackBar = snackbarHostState.showSnackbar(
                            message = "The player was deleted",
                            actionLabel = "Undo",
                            duration = SnackbarDuration.Short
                        )

                        when(snackBar) {
                            SnackbarResult.ActionPerformed -> {
                                onEvent(PlayerEvent.UnDeletePlayer(player))
                            }

                            else -> {  }
                        }
                    }
                },
                modifier = Modifier.padding(4.dp)
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}