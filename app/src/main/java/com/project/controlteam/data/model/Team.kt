package com.project.controlteam.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.Date

@Entity
data class Team (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val teamTitle: String,
    val teamSport: String,
)