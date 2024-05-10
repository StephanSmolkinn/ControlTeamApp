package com.project.controlteam.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.controlteam.repository.TeamRepository

class TeamViewModelFactory(private val teamRepository: TeamRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TeamListViewModel(teamRepository) as T
    }
}