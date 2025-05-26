package com.openclassrooms.arista.repository

import com.openclassrooms.arista.data.dao.SleepDao
import com.openclassrooms.arista.data.repository.SleepRepository
import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import java.time.LocalDateTime

class SleepRepositoryTest {
    private val mockDao: SleepDao = mock()
    private lateinit var repository: SleepRepository
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = SleepRepository(mockDao)
    }
    private val fixedTime = LocalDateTime.of(2025, 5, 22, 18, 0, 0)
    private val sleep = Sleep(
        id = 1,
        startTime = fixedTime,
        duration = 7,
        quality = 8
    )

    @Test
    fun sleepRepository_AddSleep_CallDaoInsertSleep() = runTest {
        // When
        repository.insertSleep(sleep)
        testDispatcher.scheduler.advanceUntilIdle() // Ensure coroutine completes
        // Then
        verify(mockDao).insertSleep(sleep)
    }

    @Test
    fun sleepRepository_GetAllSleeps_CallDaoGetAllSleeps() = runTest {
        // When
        repository.getAllSleeps()
        testDispatcher.scheduler.advanceUntilIdle() // Ensure coroutine completes
        // Then
        verify(mockDao).getAllSleeps()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        // Clean up any resources if needed
        Dispatchers.resetMain()
    }
}