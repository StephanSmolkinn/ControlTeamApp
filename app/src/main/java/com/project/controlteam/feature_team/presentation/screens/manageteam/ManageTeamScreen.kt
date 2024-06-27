package com.project.controlteam.feature_team.presentation.screens.manageteam

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.project.controlteam.feature_team.presentation.screens.manageteam.const_list_manage_item.ManageItemList
import com.project.controlteam.feature_team.viewmodel.events.PlayerEvent
import com.project.controlteam.feature_team.viewmodel.events.TeamEvent
import com.project.controlteam.feature_team.viewmodel.states.PlayerState
import com.project.controlteam.feature_team.viewmodel.states.TeamState

@Composable
fun ManageTeamScreen(
    navController: NavHostController,
    state: PlayerState,
    onEvent: (PlayerEvent) -> Unit,
    stateTeam: TeamState,
    onEventTeam: (TeamEvent) -> Unit,
) {
    val listManage = ManageItemList.getListManageItem()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Manage team",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(items = listManage, key = { it.title }) {
                ManageItem(
                    manageItem = it,
                    navController = navController,
                    state = state,
                    onEvent = onEvent,
                    stateTeam = stateTeam,
                    onEventTeam = onEventTeam
                )
            }
        }
    }
}

data class ManageItem(
    val title: String,
    val description: String
)