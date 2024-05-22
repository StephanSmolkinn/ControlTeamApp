package com.project.controlteam.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.project.controlteam.data.model.Player
import com.project.controlteam.data.model.Team
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

}