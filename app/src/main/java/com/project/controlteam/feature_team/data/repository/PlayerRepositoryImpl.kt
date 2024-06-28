package com.project.controlteam.feature_team.data.repository

import com.project.controlteam.feature_team.data.model.Player
import com.project.controlteam.feature_team.data.data_source.PlayerDao
import com.project.controlteam.feature_team.viewmodel.repository.PlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlayerRepositoryImpl(private val playerDao: PlayerDao) : PlayerRepository {

    override suspend fun upsertPlayer(player: Player) = withContext(Dispatchers.IO) { playerDao.upsertPlayer(player) }

    override suspend fun deletePlayer(player: Player) = withContext(Dispatchers.IO) { playerDao.deletePlayer(player) }

    override fun getAllPlayers(teamId: Int) = playerDao.getAllPlayers(teamId)

    override suspend fun getPlayerById(playerId: Int) = withContext(Dispatchers.IO) { playerDao.getPlayerById(playerId) }

    override suspend fun getSumOfSalary(teamId: Int) = withContext(Dispatchers.IO) { playerDao.getSumOfSalary(teamId) }

}