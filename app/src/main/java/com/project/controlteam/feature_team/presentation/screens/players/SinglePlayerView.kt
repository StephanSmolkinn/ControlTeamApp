package com.project.controlteam.feature_team.presentation.screens.players

import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.project.controlteam.feature_team.viewmodel.events.PlayerEvent
import com.project.controlteam.feature_team.viewmodel.states.PlayerState

@Composable
fun SinglePlayerView(
    navController: NavHostController,
    playerId: Int?,
    state: PlayerState,
    onEvent: (PlayerEvent) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = state.playerName,
            fontSize = 24.sp,
            textAlign = TextAlign.Justify,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(64.dp))
        Text(
            text = "Information",
            fontSize = 24.sp,
            textAlign = TextAlign.Justify,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(64.dp))
        Text(
            text = "Position ${state.playerPosition}",
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "Shape ${state.playerShape}",
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "Salary ${state.playerSalary}",
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(64.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        }
    }
}