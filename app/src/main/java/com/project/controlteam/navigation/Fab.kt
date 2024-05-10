package com.project.controlteam.navigation

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.project.controlteam.navigation.constants_graph_root.Graph

@Composable
fun Fab(
    navController: NavHostController,
) {
    FloatingActionButton(
        onClick = { navController.navigate(Graph.ADD_TEAM) },
        modifier = Modifier,
        shape = CircleShape
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add team")
    }
}