package com.project.controlteam.feature_training_plan.data.repository

import com.project.controlteam.feature_training_plan.data.model.day_constants.Day
import com.project.controlteam.feature_training_plan.data.model.TrainingPlan
import com.project.controlteam.feature_training_plan.data.data_source.TrainingPlanDao
import com.project.controlteam.feature_training_plan.viewmodel.repository.TrainingPlanRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TrainingPlanRepositoryImpl(private val trainingPlanDao: TrainingPlanDao) : TrainingPlanRepository {

    override suspend fun upsertTrainingPlan(trainingPlan: TrainingPlan) = withContext(Dispatchers.IO) { trainingPlanDao.upsertTrainingPlan(trainingPlan) }

    override suspend fun updateTrainingPlan(plan: String, planId: Int) = withContext(Dispatchers.IO) { trainingPlanDao.updateTrainingPlan(plan, planId) }

    override suspend fun getTrainingPlanById(trainingDay: Day, teamId: Int) = withContext(Dispatchers.IO) { trainingPlanDao.getTrainingPlanById(trainingDay, teamId) }

}