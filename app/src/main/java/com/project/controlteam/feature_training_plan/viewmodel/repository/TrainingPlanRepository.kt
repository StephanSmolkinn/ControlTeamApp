package com.project.controlteam.feature_training_plan.viewmodel.repository

import com.project.controlteam.feature_training_plan.data.model.day_constants.Day
import com.project.controlteam.feature_training_plan.data.model.TrainingPlan
import com.project.controlteam.feature_training_plan.data.data_source.TrainingPlanDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrainingPlanRepository(private val trainingPlanDao: TrainingPlanDao) {

    suspend fun insertTrainingPlan(trainingPlan: TrainingPlan) = withContext(Dispatchers.IO) { trainingPlanDao.upsertTrainingPlan(trainingPlan) }

    suspend fun updateTrainingPlan(plan: String, planId: Int) = withContext(Dispatchers.IO) { trainingPlanDao.updateTrainingPlan(plan, planId) }

    suspend fun getOneTrainingPlan(trainingDay: Day, teamId: Int) = withContext(Dispatchers.IO) { trainingPlanDao.getTrainingPlanById(trainingDay, teamId) }

}