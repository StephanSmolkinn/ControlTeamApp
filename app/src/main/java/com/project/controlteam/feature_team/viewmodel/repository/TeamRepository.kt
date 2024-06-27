package com.project.controlteam.feature_team.viewmodel.repository

import com.project.controlteam.feature_team.data.model.Team
import com.project.controlteam.feature_team.data.data_source.TeamDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TeamRepository(private val teamDao: TeamDao) {

    suspend fun insertTeam(team: Team) = withContext(Dispatchers.IO) { teamDao.upsertTeam(team) }

    suspend fun deleteTeam(team: Team) = withContext(Dispatchers.IO) { teamDao.deleteTeam(team) }

    fun getAllTeams() = teamDao.getAllTeams()

    suspend fun getOneTeam(teamId: Int) = withContext(Dispatchers.IO) { teamDao.getTeamById(teamId) }

    suspend fun getFinance(teamId: Int) = withContext(Dispatchers.IO) { teamDao.getFinance(teamId) }

    suspend fun setFinance(teamId: Int, finance: Double) = withContext(Dispatchers.IO) { teamDao.insertFinance(teamId, finance) }

}