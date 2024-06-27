package com.project.controlteam.feature_training_plan.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.project.controlteam.feature_training_plan.data.model.day_constants.Day
import com.project.controlteam.feature_training_plan.data.model.TrainingPlan

@Dao
interface TrainingPlanDao {

    @Insert
    suspend fun upsertTrainingPlan(trainingPlan: TrainingPlan)

    @Query("UPDATE trainingplan SET trainingPlan = :plan WHERE id =:planId")
    suspend fun updateTrainingPlan(plan: String, planId: Int)

    @Query("SELECT * FROM trainingplan WHERE day = :trainingDay AND teamId = :teamId")
    suspend fun getTrainingPlanById(trainingDay: Day, teamId: Int): TrainingPlan

}