package com.project.controlteam.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.controlteam.data.model.Team

@Database(
    entities = [Team::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract val teamDao: TeamDao

    companion object {

        @Volatile
        private var _instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return _instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    name = "team_database"
                ).build()
                _instance = instance
                instance
            }
        }

    }

}