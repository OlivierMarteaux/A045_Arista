package com.openclassrooms.arista.data.repository

import android.util.Log
import com.openclassrooms.arista.data.dao.SleepDao
import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class SleepRepository @Inject constructor(private val sleepDao: SleepDao) {

    // Insert sleep record
    suspend fun insertSleep(sleep: Sleep) =
        try {sleepDao.insertSleep(sleep)}
        catch (e: Exception) { Log.e("OM:SleepRepository.insertSleep", e.message.toString())}

    // Get all sleep records
    fun getAllSleeps(): Flow<List<Sleep>> =
        try {sleepDao.getAllSleeps()}
        catch (e: Exception) { Log.e("OM:SleepRepository.getAllSleeps", e.message.toString()); emptyFlow()}
}