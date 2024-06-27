package com.project.controlteam.feature_training_plan.viewmodel.state

import com.project.controlteam.feature_training_plan.data.model.day_constants.Day

data class TrainingPlanState(
    val trainingPlan: String = "",
    val day: Day = Day.MONDAY,
    val trainingPlanId: Int = 0,
    val teamId: Int = 0
)