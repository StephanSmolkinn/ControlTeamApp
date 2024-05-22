package com.project.controlteam.repository

import com.project.controlteam.data.model.Player
import com.project.controlteam.data.room.PlayerDao

class PlayerRepository(private val playerDao: PlayerDao) {

    suspend fun insertPlayer(player: Player) = playerDao.upsertPlayer(player)

    suspend fun deletePlayer(player: Player) = playerDao.deletePlayer(player)

    fun getAllPlayers(teamId: Int) = playerDao.getAllPlayers(teamId)

    suspend fun getOnePlayer(playerId: Int) = playerDao.getPlayerById(playerId)

}