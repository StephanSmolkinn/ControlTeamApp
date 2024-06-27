package com.project.controlteam.feature_team.presentation.screens.manageteam

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.project.controlteam.navigation.constants_graph_root.Graph
import com.project.controlteam.utils.PlayerStateTeamId
import com.project.controlteam.feature_team.viewmodel.events.PlayerEvent
import com.project.controlteam.feature_team.viewmodel.events.TeamEvent
import com.project.controlteam.feature_team.viewmodel.states.PlayerState
import com.project.controlteam.feature_team.viewmodel.states.TeamState

@Composable
fun ManageItem(
    manageItem: ManageItem,
    navController: NavHostController,
    state: PlayerState,
    onEvent: (PlayerEvent) -> Unit,
    stateTeam: TeamState,
    onEventTeam: (TeamEvent) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = manageItem.title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textAlign = TextAlign.Justify,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(16.dp)
                )
                Text(
                    text = manageItem.description,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textAlign = TextAlign.Justify,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
            IconButton(
                onClick = {
                    when (manageItem.title) {
                        "Training plans" -> {
                            navController.navigate(Graph.TRAINING_PLAN)
                        }
                        "Finance" -> {
                            onEvent(PlayerEvent.GetSumOfSalary(PlayerStateTeamId.teamId))
                            onEventTeam(TeamEvent.GetFinanceTeam(PlayerStateTeamId.teamId))
                            navController.navigate(Graph.FINANCE)
                        }
                    }
                },
                modifier = Modifier.padding(6.dp)
            ) {
                Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null)
            }
        }
    }
}