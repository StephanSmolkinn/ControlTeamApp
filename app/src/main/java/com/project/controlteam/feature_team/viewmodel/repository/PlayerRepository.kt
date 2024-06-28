package com.project.controlteam.feature_team.viewmodel.repository

import com.project.controlteam.feature_team.data.model.Player
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {

    suspend fun upsertPlayer(player: Player)

    suspend fun deletePlayer(player: Player)

    fun getAllPlayers(teamId: Int) : Flow<List<Player>>

    suspend fun getPlayerById(playerId: Int): Player

    suspend fun getSumOfSalary(teamId: Int): Double?

}