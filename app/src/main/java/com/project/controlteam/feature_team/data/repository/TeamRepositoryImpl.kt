package com.project.controlteam.feature_team.data.repository

import com.project.controlteam.feature_team.data.model.Team
import com.project.controlteam.feature_team.data.data_source.TeamDao
import com.project.controlteam.feature_team.viewmodel.repository.TeamRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TeamRepositoryImpl(private val teamDao: TeamDao) : TeamRepository {

    override suspend fun insertTeam(team: Team) = withContext(Dispatchers.IO) { teamDao.upsertTeam(team) }

    override suspend fun deleteTeam(team: Team) = withContext(Dispatchers.IO) { teamDao.deleteTeam(team) }

    override fun getAllTeams() = teamDao.getAllTeams()

    override suspend fun getTeamById(teamId: Int) = withContext(Dispatchers.IO) { teamDao.getTeamById(teamId) }

    override suspend fun getFinance(teamId: Int) = withContext(Dispatchers.IO) { teamDao.getFinance(teamId) }

    override suspend fun insertFinance(teamId: Int, finance: Double) = withContext(Dispatchers.IO) { teamDao.insertFinance(teamId, finance) }

}