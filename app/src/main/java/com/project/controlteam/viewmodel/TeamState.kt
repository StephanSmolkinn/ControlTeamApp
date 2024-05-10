package com.project.controlteam.viewmodel

import com.project.controlteam.data.model.Team

data class TeamState(
    val teams: List<Team> = emptyList(),
    val teamTitle: String = "",
    val teamSport: String = "",
)
