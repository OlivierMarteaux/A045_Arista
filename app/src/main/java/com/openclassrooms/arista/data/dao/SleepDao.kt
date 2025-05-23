package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.flow.Flow

@Dao
interface SleepDao {
    @Upsert
    suspend fun insertSleep(sleep: Sleep)
    @Query("SELECT * FROM Sleep WHERE id = :id")
    suspend fun getSleepById(id: Int): Sleep?
    @Query("SELECT * FROM Sleep")
    fun getAllSleeps(): Flow<List<Sleep>>
}