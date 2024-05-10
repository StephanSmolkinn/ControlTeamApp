package com.project.controlteam.viewmodel

import com.project.controlteam.data.model.Team
import kotlinx.coroutines.Job
import java.time.LocalDateTime

data class TeamListState(
    val teams: List<Team> = emptyList(),
    val teamTitle: String = "",
    val teamSport: String = "",
)
