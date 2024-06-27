package com.project.controlteam.feature_team.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.project.controlteam.feature_team.data.model.Player
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {

    @Upsert
    suspend fun upsertPlayer(player: Player)

    @Delete
    suspend fun deletePlayer(player: Player)

    @Query("SELECT * FROM player WHERE teamId = :teamId")
    fun getAllPlayers(teamId: Int) : Flow<List<Player>>

    @Query("SELECT * FROM player WHERE id = :playerId")
    suspend fun getPlayerById(playerId: Int): Player

    @Query("SELECT SUM(salary) FROM player WHERE teamId = :teamId")
    suspend fun getSumOfSalary(teamId: Int): Double?

}