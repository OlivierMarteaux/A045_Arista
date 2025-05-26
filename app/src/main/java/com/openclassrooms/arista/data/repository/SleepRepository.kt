package com.openclassrooms.arista.data.repository

import android.util.Log
import com.openclassrooms.arista.data.dao.SleepDao
import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

/**
 * Repository for managing sleep-related operations.
 *
 * Provides methods to interact with [SleepDao] while handling exceptions gracefully.
 *
 * @property sleepDao The DAO used to access sleep data from the local database.
 */
class SleepRepository @Inject constructor(private val sleepDao: SleepDao) {

    /**
     * Inserts a new [Sleep] record or updates it if it already exists.
     *
     * @param sleep The sleep record to insert.
     * Logs an error if an exception occurs.
     */
    suspend fun insertSleep(sleep: Sleep) =
        try {sleepDao.insertSleep(sleep)}
        catch (e: Exception) { Log.e("OM:SleepRepository.insertSleep", e.message.toString())}

    /**
     * Retrieves a flow of all [Sleep] records.
     *
     * @return A [Flow] emitting the list of sleep entries.
     * Logs an error and returns an empty flow if an exception occurs.
     */
    fun getAllSleeps(): Flow<List<Sleep>> =
        try {sleepDao.getAllSleeps()}
        catch (e: Exception) { Log.e("OM:SleepRepository.getAllSleeps", e.message.toString()); emptyFlow()}
}