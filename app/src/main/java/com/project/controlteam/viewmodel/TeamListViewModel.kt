package com.project.controlteam.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.controlteam.data.model.Team
import com.project.controlteam.data.room.TeamDao
import com.project.controlteam.repository.TeamRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class TeamListViewModel(
    private val teamRepository: TeamRepository
) : ViewModel() {

    private val teams = teamRepository.getAllTeams()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(TeamListState())
    val state = combine(_state, teams) { state, teams ->
        state.copy(
            teams = teams
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TeamListState())

    fun onTeamListEvent(event: TeamListEvent) {
        when (event) {
            TeamListEvent.AddTeam -> {
                val teamTitle = state.value.teamTitle
                val teamSport = state.value.teamSport

                if (teamTitle.isEmpty() || teamSport.isEmpty())
                    return

                val team = Team(
                    teamTitle = teamTitle,
                    teamSport = teamSport,
                )

                viewModelScope.launch {
                    teamRepository.insertTeam(team)
                }

                _state.update {
                    it.copy(
                        teamTitle = "",
                        teamSport = ""
                    )
                }
            }

            is TeamListEvent.DeleteTeam -> {
                viewModelScope.launch {
                    teamRepository.deleteTeam(event.team)
                }
            }

            is TeamListEvent.SetTeamSport -> {
                _state.update {
                    it.copy(teamSport = event.teamSport)
                }
            }

            is TeamListEvent.SetTeamTitle -> {
                _state.update {
                    it.copy(teamTitle = event.teamTitle)
                }
            }

            is TeamListEvent.UnDeleteTeam -> {
                viewModelScope.launch {
                    teamRepository.insertTeam(event.team)
                }
            }

        }
    }
}