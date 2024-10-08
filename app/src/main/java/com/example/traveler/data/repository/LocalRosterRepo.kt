package com.example.traveler.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.traveler.data.room.storage.entity.LocalRosterModel
import com.example.traveler.data.room.storage.root.AppDatabase
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class LocalRosterRepo(context: Context) {
    private val appDatabase: AppDatabase = AppDatabase.getDatabase(context)
    private val executor: Executor = Executors.newSingleThreadExecutor()

    suspend fun insertRosterData(localRosterModel: LocalRosterModel){
        executor.execute{
            appDatabase.localRosterDao().insertRosterData(localRosterModel)
        }
    }

    suspend fun updateRosterData(localRosterModel: LocalRosterModel){
        executor.execute{
            appDatabase.localRosterDao().updateRosterData(localRosterModel)
        }
    }

    suspend fun deleteRosterData(localRosterModel: LocalRosterModel){
        executor.execute{
            appDatabase.localRosterDao().deleteRosterData(localRosterModel)
        }
    }

    suspend fun getAllRosterDataLive(): LiveData<List<LocalRosterModel>>{
        return appDatabase.localRosterDao().getAllRosterDataLive()
    }

}