package com.project.controlteam.viewmodel.events.states

import com.project.controlteam.data.model.Player

data class PlayerState(
    val players: List<Player> = emptyList(),
    val playerName: String = "",
    val playerPosition: String = "",
    val playerShape: String = "Normal",
    val playerSalary: String = "0.0",
    val playerId: Int = 0,
    val teamId: Int = 0
)