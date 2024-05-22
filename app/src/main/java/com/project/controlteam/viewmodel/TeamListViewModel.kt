package com.project.controlteam.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.controlteam.data.model.Team
import com.project.controlteam.repository.TeamRepository
import com.project.controlteam.viewmodel.events.TeamEvent
import com.project.controlteam.viewmodel.events.states.TeamState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamListViewModel @Inject constructor(
    private val teamRepository: TeamRepository
) : ViewModel() {

    private val teams = teamRepository.getAllTeams()

    private val _state = MutableStateFlow(TeamState())
    val state = combine(_state, teams) { state, teams ->
        state.copy(
            teams = teams
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TeamState())

    fun onTeamListEvent(event: TeamEvent) {
        when (event) {
            TeamEvent.AddTeam -> {

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

            is TeamEvent.DeleteTeam -> {
                viewModelScope.launch {
                    teamRepository.deleteTeam(event.team)
                }
            }

            is TeamEvent.SetTeamSport -> {
                _state.update {
                    it.copy(teamSport = event.teamSport)
                }
            }

            is TeamEvent.SetTeamTitle -> {
                _state.update {
                    it.copy(teamTitle = event.teamTitle)
                }
            }

            is TeamEvent.UnDeleteTeam -> {
                viewModelScope.launch {
                    teamRepository.insertTeam(event.team)
                }
            }

            is TeamEvent.GetOneTeam -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val team = teamRepository.getOneTeam(event.teamId)
                    _state.update {
                        it.copy(
                            teamTitle = team.teamTitle,
                            teamSport = team.teamSport,
                            teamId = team.id
                        )
                    }
                }
            }

            is TeamEvent.SetTeamId -> {
                _state.update {
                    it.copy(teamId = state.value.teamId)
                }
            }
        }
    }
}