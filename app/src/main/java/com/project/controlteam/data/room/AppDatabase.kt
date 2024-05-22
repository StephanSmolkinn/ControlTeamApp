package com.project.controlteam.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.controlteam.data.model.Player
import com.project.controlteam.data.model.Team

@Database(
    entities = [Team::class, Player::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract val teamDao: TeamDao

    abstract val playerDao: PlayerDao

}