package com.project.controlteam.feature_team.presentation.screens.hometeam


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.project.controlteam.navigation.HomeNavGraph
import com.project.controlteam.utils.PlayerStateTeamId
import com.project.controlteam.feature_team.viewmodel.events.TeamEvent
import com.project.controlteam.feature_team.viewmodel.states.TeamState

@Composable
fun HomeTeamScreen(
    navController: NavHostController = rememberNavController(),
    teamId: Int?,
    stateTeam: TeamState,
    onEventTeam: (TeamEvent) -> Unit,
) {
    PlayerStateTeamId.teamId = teamId ?: -1
    Scaffold {
        HomeNavGraph(
            navController = navController,
            modifier = Modifier.padding(it),
            stateTeam = stateTeam,
            onEventTeam = onEventTeam,
        )
    }
}