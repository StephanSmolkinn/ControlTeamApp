package com.project.controlteam.feature_team.presentation.common_components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.project.controlteam.feature_team.viewmodel.events.TeamEvent
import com.project.controlteam.navigation.constants_graph_root.Graph

@Composable
fun FabCustom(
    text: String,
    onEvent: () -> Unit
) {
    FloatingActionButton(
        onClick = onEvent,
        modifier = Modifier,
        shape = CircleShape
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = text)
    }
}