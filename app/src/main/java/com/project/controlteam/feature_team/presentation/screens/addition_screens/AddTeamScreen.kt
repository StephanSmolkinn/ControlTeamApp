package com.project.controlteam.feature_team.presentation.screens.addition_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.project.controlteam.feature_team.viewmodel.events.TeamEvent
import com.project.controlteam.feature_team.viewmodel.states.TeamState

@Composable
fun AddTeamScreen(
    navController: NavHostController,
    state: TeamState,
    onEvent: (TeamEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(
            value = state.teamTitle,
            onValueChange = { onEvent(TeamEvent.SetTeamTitle(it)) },
            placeholder = { Text(text = "Team name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = state.teamSport,
            onValueChange = { onEvent(TeamEvent.SetTeamSport(it)) },
            placeholder = { Text(text = "Team sport") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                onEvent(TeamEvent.AddTeam)
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Create team", fontSize = 18.sp)
        }
    }
}
