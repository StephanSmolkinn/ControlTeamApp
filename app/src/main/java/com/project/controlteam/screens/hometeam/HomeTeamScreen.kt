package com.project.controlteam.screens.hometeam


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.project.controlteam.navigation.HomeNavGraph
import com.project.controlteam.ui.navigationbar.NavBar

@Composable
fun HomeTeamScreen(
    navController: NavHostController = rememberNavController(),
    teamId: Int?
) {
    Scaffold(
        bottomBar = { NavBar(navController = navController) }
    ) {
        HomeNavGraph(
            navController = navController,
            teamId = teamId,
            modifier = Modifier.padding(it)
        )
    }
}