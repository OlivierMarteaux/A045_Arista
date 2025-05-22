package com.openclassrooms.arista

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.openclassrooms.arista.data.AristaDatabase
import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class SleepDaoTest {
    private lateinit var database: AristaDatabase

    @Before
    // Given a database and a sleeps list:
    fun createDb(){
        database = Room
            .inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                AristaDatabase::class.java
            )
            .allowMainThreadQueries()
            .build()
    }
    private val fixedTime = LocalDateTime.of(2025, 5, 22, 18, 0, 0)
    private val sleep1 = Sleep(
        id = 1,
        startTime = fixedTime,
        duration = 7,
        quality = 8
    )
    private val sleep2 = Sleep(
        id = 2,
        startTime = fixedTime,
        duration = 6,
        quality = 5
    )
    private val sleep3 = Sleep(
        id = 3,
        startTime = fixedTime,
        duration = 8,
        quality = 9
    )
    private val sleepList = listOf(sleep1, sleep2, sleep3)

    @Test
    fun sleepDao_InsertSleep_ReturnsCorrectSleep() = runTest {
        // Given sleep is inserted in the database
        database.sleepDao().insertSleep(sleep1)
        // When the sleep is retrieved from the database
        val retrievedSleep = database.sleepDao().getSleepById(sleep1.id)
        // Then the retrieved sleep is the same as the inserted sleep
        assertEquals(sleep1, retrievedSleep)
    }

    @Test
    fun sleepDao_GetAllSleeps_ReturnsCorrectSleepList() = runTest {
        // Given sleeps are inserted in the database
        sleepList.forEach { database.sleepDao().insertSleep(it) }
        // When the sleeps are retrieved from the database
        database.sleepDao().getAllSleeps().test{
            val retrievedSleeps = awaitItem()
            // Then the retrieved sleeps are the same as the inserted sleeps
            assertEquals(sleepList, retrievedSleeps)
            cancel()
        }
    }

    @After
    // close the database instance after test
    fun closeDb(){
        database.close()
    }
}