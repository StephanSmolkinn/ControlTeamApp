package com.project.controlteam.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(entity = Team::class, parentColumns = ["id"], childColumns = ["teamId"])
])
data class Player(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val playerName: String,
    val playerPosition: String,
    val playerShape: String,
    val salary: Double,
    val teamId: Int
)