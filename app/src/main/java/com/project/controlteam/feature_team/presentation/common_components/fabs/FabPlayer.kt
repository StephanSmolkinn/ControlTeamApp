package com.project.controlteam.feature_team.presentation.common_components.fabs

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.project.controlteam.navigation.constants_graph_root.Graph
import com.project.controlteam.feature_team.viewmodel.events.PlayerEvent
import com.project.controlteam.feature_team.viewmodel.states.PlayerState

@Composable
fun FabPlayer(
    navController: NavHostController,
    state: PlayerState,
    onEvent: (PlayerEvent) -> Unit
) {
    FloatingActionButton(
        onClick = {
            onEvent(PlayerEvent.SetPlayerName(""))
            onEvent(PlayerEvent.SetPlayerPosition(""))
            onEvent(PlayerEvent.SetPlayerShape(""))
            onEvent(PlayerEvent.SetPlayerSalary("0.0"))
            navController.navigate(Graph.ADD_PLAYER)
        },
        modifier = Modifier.padding(8.dp),
        shape = CircleShape
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add player")
    }
}