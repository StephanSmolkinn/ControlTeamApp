package com.project.controlteam.repository

import com.project.controlteam.data.model.Team
import com.project.controlteam.data.room.TeamDao

class TeamRepository(private val teamDao: TeamDao) {

    suspend fun insertTeam(team: Team) = teamDao.upsertTeam(team)

    suspend fun deleteTeam(team: Team) = teamDao.deleteTeam(team)

    fun getAllTeams() = teamDao.getAllTeams()

    suspend fun getOneTeam(teamId: Int) = teamDao.getTeamById(teamId)

}