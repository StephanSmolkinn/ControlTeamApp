package com.project.controlteam.feature_team.viewmodel.repository

import com.project.controlteam.feature_team.data.model.Player
import com.project.controlteam.feature_team.data.data_source.PlayerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlayerRepository(private val playerDao: PlayerDao) {

    suspend fun insertPlayer(player: Player) = withContext(Dispatchers.IO) { playerDao.upsertPlayer(player) }

    suspend fun deletePlayer(player: Player) = withContext(Dispatchers.IO) { playerDao.deletePlayer(player) }

    fun getAllPlayers(teamId: Int) = playerDao.getAllPlayers(teamId)

    suspend fun getOnePlayer(playerId: Int) = withContext(Dispatchers.IO) { playerDao.getPlayerById(playerId) }

    suspend fun getSumOfSalary(teamId: Int) = withContext(Dispatchers.IO) { playerDao.getSumOfSalary(teamId) }

}