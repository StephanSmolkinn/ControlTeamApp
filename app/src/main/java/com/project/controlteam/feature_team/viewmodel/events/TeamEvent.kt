package com.project.controlteam.feature_team.viewmodel.events

import com.project.controlteam.feature_team.data.model.Team

sealed interface TeamEvent {

    object AddTeam : TeamEvent

    data class SetTeamTitle(val teamTitle: String) : TeamEvent

    data class SetTeamSport(val teamSport: String) : TeamEvent

    data class DeleteTeam(val team: Team) : TeamEvent

    data class UnDeleteTeam(val team: Team) : TeamEvent

    data class GetOneTeam(val teamId: Int) : TeamEvent

    data class SetTeamId(val teamId: Int) : TeamEvent

    data class SetFinanceTeam(val finance: String) : TeamEvent

    data class GetFinanceTeam(val teamId: Int) : TeamEvent

    data class GetRemainderFinance(val salary: Double?) : TeamEvent

    data class UpdateFinance(val teamId: Int, val finance: String) : TeamEvent

}
