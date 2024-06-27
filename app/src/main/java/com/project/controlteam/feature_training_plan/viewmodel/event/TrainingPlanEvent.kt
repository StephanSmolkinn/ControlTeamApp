package com.project.controlteam.feature_training_plan.viewmodel.event

import com.project.controlteam.feature_training_plan.data.model.day_constants.Day

sealed interface TrainingPlanEvent {

    object AddTrainingPlan : TrainingPlanEvent

    data class UpdateTrainingPlan(val plan: String, val trainingPlanId: Int) : TrainingPlanEvent

    data class SetTrainingPlan(val trainingPlan: String) : TrainingPlanEvent

    data class SetDay(val day: Day) : TrainingPlanEvent

    data class SetTrainingPlainId(val trainingPlainId: Int) : TrainingPlanEvent

    data class GetOneTrainingPlan(val trainingDay: Day, val teamId: Int) : TrainingPlanEvent


}