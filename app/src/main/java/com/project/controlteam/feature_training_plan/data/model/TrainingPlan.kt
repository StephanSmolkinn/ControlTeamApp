package com.project.controlteam.feature_training_plan.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.project.controlteam.feature_team.data.model.Team
import com.project.controlteam.feature_training_plan.data.model.day_constants.Day

@Entity(foreignKeys = [
    ForeignKey(entity = Team::class, parentColumns = ["id"], childColumns = ["teamId"])
])
data class TrainingPlan(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val trainingPlan: String,
    val day: Day,
    val teamId: Int
)