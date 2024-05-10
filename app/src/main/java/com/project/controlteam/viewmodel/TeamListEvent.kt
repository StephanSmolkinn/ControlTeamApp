package com.project.controlteam.viewmodel

import com.project.controlteam.data.model.Team

sealed interface TeamListEvent {

    object AddTeam : TeamListEvent

    data class SetTeamTitle(val teamTitle: String) : TeamListEvent

    data class SetTeamSport(val teamSport: String) : TeamListEvent

    data class DeleteTeam(val team: Team) : TeamListEvent

    data class UnDeleteTeam(val team: Team) : TeamListEvent

}
