package com.project.controlteam.feature_team.viewmodel.states

import com.project.controlteam.feature_team.data.model.Player

data class PlayerState(
    val players: List<Player> = emptyList(),
    val playerName: String = "",
    val playerPosition: String = "",
    val playerShape: String = "Normal",
    val playerSalary: String = "0.0",
    val sumOfSalary: Double? = 0.0,
    val playerId: Int = 0,
    val teamId: Int = 0
)