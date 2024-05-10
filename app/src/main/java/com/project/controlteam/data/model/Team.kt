package com.project.controlteam.data.model

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.Date

@Immutable
@Entity
data class Team (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val teamTitle: String,
    val teamSport: String,
)