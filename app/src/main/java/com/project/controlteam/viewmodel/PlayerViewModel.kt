package com.project.controlteam.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.controlteam.data.model.Player
import com.project.controlteam.repository.PlayerRepository
import com.project.controlteam.utils.PlayerStateTeamId
import com.project.controlteam.viewmodel.events.PlayerEvent
import com.project.controlteam.viewmodel.events.states.PlayerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val playerRepository: PlayerRepository
) : ViewModel() {

    private val _statePlayer = MutableStateFlow(PlayerState())

    val players = playerRepository.getAllPlayers(PlayerStateTeamId.teamId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val statePlayer = combine(_statePlayer, players) { state, players ->
        state.copy(
            players = players
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PlayerState())


    fun onPlayerEvent(event: PlayerEvent) {
        when (event) {
            PlayerEvent.AddPlayer -> {
                val playerName = statePlayer.value.playerName
                val playerPosition = statePlayer.value.playerPosition
                var playerSalary = statePlayer.value.playerSalary
                val playerShape = statePlayer.value.playerShape
                val playerTeamId = PlayerStateTeamId.teamId

                try {
                    playerSalary.toDouble()
                } catch (e: Exception) {
                    playerSalary = "0.0"
                }

                if (playerName.isEmpty() || playerPosition.isEmpty())
                    return

                val player = Player(
                    playerName = playerName,
                    playerPosition = playerPosition,
                    playerShape = playerShape,
                    salary = playerSalary.toDouble(),
                    teamId = playerTeamId
                )

                viewModelScope.launch {
                    playerRepository.insertPlayer(player)
                }

                _statePlayer.update {
                    it.copy(
                        playerName = "",
                        playerPosition = "",
                        playerShape = "Normal",
                        playerSalary = "0.0",
                        teamId = 0
                    )
                }
            }

            is PlayerEvent.DeletePlayer -> {
                viewModelScope.launch {
                    playerRepository.deletePlayer(event.player)
                }
            }

            is PlayerEvent.SetPlayerName -> {
                _statePlayer.update {
                    it.copy(playerName = event.playerName)
                }
            }

            is PlayerEvent.SetPlayerPosition -> {
                _statePlayer.update {
                    it.copy(playerPosition = event.playerPosition)
                }
            }

            is PlayerEvent.SetPlayerSalary -> {
                _statePlayer.update {
                    it.copy(playerSalary = event.playerSalary)
                }
            }

            is PlayerEvent.SetPlayerShape -> {
                _statePlayer.update {
                    it.copy(playerShape = event.playerShape)
                }
            }

            is PlayerEvent.UnDeletePlayer -> {
                viewModelScope.launch {
                    playerRepository.insertPlayer(event.player)
                }
            }

            is PlayerEvent.GetOnePlayer -> {

            }

        }
    }
}