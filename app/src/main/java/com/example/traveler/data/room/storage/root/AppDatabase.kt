package com.example.traveler.data.room.storage.root

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.traveler.data.room.storage.dao.LocalRosterDao
import com.example.traveler.data.room.storage.entity.LocalRosterModel
import java.util.Objects


const val DATABASE_NAME: String = "project_database.db"


@Database(entities = [LocalRosterModel::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
abstract fun localRosterDao(): LocalRosterDao
}