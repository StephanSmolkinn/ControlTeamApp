package com.project.controlteam.feature_team.viewmodel.events

import com.project.controlteam.feature_team.data.model.Player

sealed interface PlayerEvent {

    object AddPlayer : PlayerEvent

    data class SetPlayerName(val playerName: String) : PlayerEvent

    data class SetPlayerPosition(val playerPosition: String) : PlayerEvent

    data class SetPlayerShape(val playerShape: String) : PlayerEvent

    data class SetPlayerSalary(val playerSalary: String) : PlayerEvent

    data class DeletePlayer(val player: Player) : PlayerEvent

    data class UnDeletePlayer(val player: Player) : PlayerEvent

    data class GetOnePlayer(val playerId: Int) : PlayerEvent

    data class GetSumOfSalary(val teamId: Int) : PlayerEvent

}