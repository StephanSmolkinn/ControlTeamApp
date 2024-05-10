package com.project.controlteam.screens.additionteam

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.project.controlteam.navigation.constants_graph_root.Graph
import com.project.controlteam.viewmodel.TeamListEvent
import com.project.controlteam.viewmodel.TeamListState

@Composable
fun AddTeamScreen(
    navController: NavHostController,
    state: TeamListState,
    onEvent: (TeamListEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(
            value = state.teamTitle,
            onValueChange = { onEvent(TeamListEvent.SetTeamTitle(it)) },
            label = { Text(text = "Team name") },
            placeholder = { Text(text = "Team name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = state.teamSport,
            onValueChange = { onEvent(TeamListEvent.SetTeamSport(it)) },
            label = { Text(text = "Team sport") },
            placeholder = { Text(text = "Team name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                onEvent(TeamListEvent.AddTeam)
                navController.navigate(Graph.TEAM_LIST)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Create team", fontSize = 18.sp)
        }
    }
}
