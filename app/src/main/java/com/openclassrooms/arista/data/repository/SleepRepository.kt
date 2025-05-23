package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.SleepDao
import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//class SleepRepository(private val apiService: FakeApiService = FakeApiService()) {
//
//    // Get all sleep records
//    val allSleeps: List<Sleep> get() = apiService.getAllSleeps()
//}

class SleepRepository @Inject constructor(private val sleepDao: SleepDao) {

    // Insert sleep record
    suspend fun insertSleep(sleep: Sleep) {sleepDao.insertSleep(sleep)}

    // Get all sleep records
    fun getAllSleeps(): Flow<List<Sleep>> {return sleepDao.getAllSleeps()}
}