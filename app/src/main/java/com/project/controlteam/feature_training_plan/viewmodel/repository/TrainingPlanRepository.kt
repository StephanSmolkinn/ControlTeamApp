package com.project.controlteam.feature_training_plan.viewmodel.repository

import com.project.controlteam.feature_training_plan.data.model.TrainingPlan
import com.project.controlteam.feature_training_plan.data.model.day_constants.Day

interface TrainingPlanRepository {

    suspend fun upsertTrainingPlan(trainingPlan: TrainingPlan)

    suspend fun updateTrainingPlan(plan: String, planId: Int)

    suspend fun getTrainingPlanById(trainingDay: Day, teamId: Int): TrainingPlan

}