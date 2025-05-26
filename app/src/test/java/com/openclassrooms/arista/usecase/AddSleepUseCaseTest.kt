package com.openclassrooms.arista.usecase

import com.openclassrooms.arista.data.repository.SleepRepository
import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.usecase.AddSleepUseCase
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

class AddSleepUseCaseTest {
    private val mockRepository: SleepRepository = mock()
    private lateinit var useCase: AddSleepUseCase
    private val testDispatcher = StandardTestDispatcher()

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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        useCase = AddSleepUseCase(mockRepository)
    }

    @Test
    fun addNewSleepUseCase_Execute_CallRepositoryInsertSleep() = runTest {
        // Given
        val sleep = sleep1

        // When
        useCase.execute(sleep1)
        testDispatcher.scheduler.advanceUntilIdle() // Ensure coroutine completes

        // Then
        verify(mockRepository).insertSleep(sleep)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        // Clean up any resources if needed
        Dispatchers.resetMain()
    }
}