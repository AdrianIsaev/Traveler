package com.example.traveler.data.room.storage.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.traveler.data.room.storage.entity.LocalRosterModel

@Dao
interface LocalRosterDao {

@Insert (onConflict = OnConflictStrategy.REPLACE)
fun insertRosterData(localRosterModel: LocalRosterModel)

@Update
fun updateRosterData(localRosterModel: LocalRosterModel)

@Delete
fun deleteRosterData(localRosterModel: LocalRosterModel)

@Query("SELECT * FROM rosterModel")
fun getAllRosterDataLive():LiveData<List<LocalRosterModel>>

@Query("SELECT * FROM rosterModel WHERE p_id=:id")
fun getRosterLine(id: Int): LocalRosterModel

@Query("DELETE FROM rosterModel")
fun deleteAllRosterData()

}