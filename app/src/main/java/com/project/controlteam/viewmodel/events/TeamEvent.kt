package com.project.controlteam.viewmodel.events

import com.project.controlteam.data.model.Team

sealed interface TeamEvent {

    object AddTeam : TeamEvent

    data class SetTeamTitle(val teamTitle: String) : TeamEvent

    data class SetTeamSport(val teamSport: String) : TeamEvent

    data class DeleteTeam(val team: Team) : TeamEvent

    data class UnDeleteTeam(val team: Team) : TeamEvent

    data class GetOneTeam(val teamId: Int) : TeamEvent

    data class SetTeamId(val teamId: Int) : TeamEvent
}
