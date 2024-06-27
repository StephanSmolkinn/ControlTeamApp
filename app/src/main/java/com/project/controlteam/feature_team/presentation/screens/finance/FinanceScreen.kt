package com.project.controlteam.feature_team.presentation.screens.finance

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Upload
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.project.controlteam.utils.PlayerStateTeamId
import com.project.controlteam.feature_team.viewmodel.events.TeamEvent
import com.project.controlteam.feature_team.viewmodel.states.PlayerState
import com.project.controlteam.feature_team.viewmodel.states.TeamState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinanceScreen(
    navController: NavHostController,
    state: PlayerState,
    stateTeam: TeamState,
    onEventTeam: (TeamEvent) -> Unit,
) {
    onEventTeam(TeamEvent.GetRemainderFinance(state.sumOfSalary ?: 0.0))
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Finance",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(24.dp)
        )
    }
    Column(
        modifier = Modifier.padding(vertical = 64.dp)
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        TextField(
            value = stateTeam.finance,
            onValueChange = {
                onEventTeam(TeamEvent.SetFinanceTeam(it))
            },
            label = { Text(text = "Set up finance", fontSize = 14.sp) },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Check,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            onEventTeam(TeamEvent.UpdateFinance(PlayerStateTeamId.teamId, stateTeam.finance))
                        }
                        .size(20.dp)
                )
            },
            modifier = Modifier
                .width(250.dp)
                .padding(start = 32.dp),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Salaries ${state.sumOfSalary}",
            fontSize = 20.sp,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(start = 32.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Remainder ${stateTeam.remainder}",
            fontSize = 20.sp,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(start = 32.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun FinanceScreePreview() {
}