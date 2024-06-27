package com.project.controlteam.feature_team.viewmodel.states

import com.project.controlteam.feature_team.data.model.Team

data class TeamState(
    val teams: List<Team> = emptyList(),
    val teamTitle: String = "",
    val teamSport: String = "",
    val finance: String = "0.0",
    val remainder: Double = 0.0,
    val teamId: Int = 0
)
