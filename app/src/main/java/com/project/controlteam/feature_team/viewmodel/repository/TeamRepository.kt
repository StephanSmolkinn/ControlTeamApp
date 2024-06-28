package com.project.controlteam.feature_team.viewmodel.repository

import com.project.controlteam.feature_team.data.model.Team
import kotlinx.coroutines.flow.Flow

interface TeamRepository {

    suspend fun insertTeam(team: Team)

    suspend fun deleteTeam(team: Team)

    fun getAllTeams(): Flow<List<Team>>

    suspend fun getTeamById(teamId: Int): Team

    suspend fun getFinance(teamId: Int) : Double?

    suspend fun insertFinance(teamId: Int, finance: Double)

}