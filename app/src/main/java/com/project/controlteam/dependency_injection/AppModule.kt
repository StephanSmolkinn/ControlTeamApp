package com.project.controlteam.dependency_injection

import android.app.Application
import androidx.room.Room
import com.project.controlteam.data.room.AppDatabase
import com.project.controlteam.repository.PlayerRepository
import com.project.controlteam.repository.TeamRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Volatile
    private var _instance: AppDatabase? = null

    @Provides
    @Singleton
    fun getDatabase(app: Application): AppDatabase {
        return _instance ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                app,
                AppDatabase::class.java,
                name = "team_database"
            ).build()
            _instance = instance
            instance
        }
    }

    @Provides
    @Singleton
    fun provideTeamRepository(database: AppDatabase): TeamRepository {
        return TeamRepository(database.teamDao)
    }

    @Provides
    @Singleton
    fun providePlayerRepository(database: AppDatabase): PlayerRepository {
        return PlayerRepository(database.playerDao)
    }

}