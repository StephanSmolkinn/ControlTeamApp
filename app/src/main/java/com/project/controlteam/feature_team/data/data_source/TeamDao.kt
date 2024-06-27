package com.project.controlteam.feature_team.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.project.controlteam.feature_team.data.model.Team
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {

    @Upsert
    suspend fun upsertTeam(team: Team)

    @Delete
    suspend fun deleteTeam(team: Team)

    @Query("SELECT * FROM team")
    fun getAllTeams() : Flow<List<Team>>

    @Query("SELECT * FROM team WHERE id = :teamId")
    suspend fun getTeamById(teamId: Int): Team

    @Query("SELECT finance FROM team WHERE id = :teamId")
    suspend fun getFinance(teamId: Int) : Double?

    @Query("UPDATE team SET finance = :finance WHERE id = :teamId")
    suspend fun insertFinance(teamId: Int, finance: Double)

}