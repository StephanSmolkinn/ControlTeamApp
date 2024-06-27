package com.project.controlteam.feature_team.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.controlteam.feature_team.data.model.Player
import com.project.controlteam.feature_team.data.model.Team
import com.project.controlteam.feature_training_plan.data.model.TrainingPlan
import com.project.controlteam.feature_training_plan.data.data_source.TrainingPlanDao

@Database(
    entities = [Team::class, Player::class, TrainingPlan::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract val teamDao: TeamDao

    abstract val playerDao: PlayerDao

    abstract val trainingPlanDao: TrainingPlanDao

}