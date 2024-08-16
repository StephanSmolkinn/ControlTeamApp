package com.project.controlteam.feature_team.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.controlteam.feature_team.data.model.Team
import com.project.controlteam.feature_team.viewmodel.events.TeamEvent
import com.project.controlteam.feature_team.viewmodel.repository.TeamRepository
import com.project.controlteam.feature_team.viewmodel.states.TeamState
import com.project.controlteam.utils.PlayerStateTeamId
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
class TeamViewModel @Inject constructor(
    private val teamRepository: TeamRepository
) : ViewModel() {

    private val teams = teamRepository.getAllTeams()

    private val _state = MutableStateFlow(TeamState())
    val state = combine(_state, teams) { state, teams ->
        state.copy(
            teams = teams
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TeamState())

    fun onTeamEvent(event: TeamEvent) {
        when (event) {
            TeamEvent.AddTeam -> {

                val teamTitle = state.value.teamTitle
                val teamSport = state.value.teamSport
                var finance = "0.0"

                try {
                    finance.toDouble()
                } catch (e: Exception) {
                    finance = "0.0"
                }

                if (teamTitle.isEmpty() || teamSport.isEmpty())
                    return

                val team = Team(
                    teamTitle = teamTitle,
                    teamSport = teamSport,
                    finance = finance.toDouble()
                )

                viewModelScope.launch {
                    teamRepository.insertTeam(team)
                }

                _state.update {
                    it.copy(
                        teamTitle = "",
                        teamSport = "",
                        finance = "0.0",
                        remainder = 0.0
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
                viewModelScope.launch {
                    val team = teamRepository.getTeamById(event.teamId)
                    _state.update {
                        it.copy(
                            teamTitle = team.teamTitle,
                            teamSport = team.teamSport,
                            finance = team.finance.toString(),
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

            is TeamEvent.SetFinanceTeam -> {
                _state.update {
                    it.copy(finance = event.finance)
                }
            }

            is TeamEvent.GetFinanceTeam -> {
                viewModelScope.launch {
                    val financeTeam = teamRepository.getFinance(event.teamId)
                    financeTeam?.let {
                        _state.update {
                            it.copy(finance = financeTeam.toString())
                        }
                    }
                }
            }

            is TeamEvent.GetRemainderFinance -> {
                viewModelScope.launch(Dispatchers.Default) {
                    event.salary?.let { salary ->
                        _state.update {
                            try {
                                it.copy(remainder = state.value.finance.toDouble() - salary)
                            } catch (e: Exception) {
                                it.copy(remainder = 0.0)
                            }
                        }
                    }
                }
            }

            is TeamEvent.UpdateFinance -> {
                viewModelScope.launch {
                    val financeTeam = try {
                        event.finance.toDouble()
                    } catch (e: Exception) {
                        "0.0".toDouble()
                    }
                    if (financeTeam != 0.0) {
                        teamRepository.insertFinance(PlayerStateTeamId.teamId, financeTeam)
                    }
                }
            }
        }
    }
}