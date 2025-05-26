package com.openclassrooms.arista.repository

import com.openclassrooms.arista.data.dao.ExerciseDao
import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
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

class ExerciseRepositoryTest {
    private val mockDao: ExerciseDao = mock()
    private lateinit var repository: ExerciseRepository
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = ExerciseRepository(mockDao)
    }
    private val fixedTime = LocalDateTime.of(2025, 5, 22, 18, 0, 0)
    private val exercise = Exercise(
        id = 1,
        startTime = fixedTime,
        duration = 30,
        category = ExerciseCategory.Running,
        intensity = 7
    )

    @Test
    fun exerciseRepository_AddExercise_CallDaoInsertExercise() = runTest {
        // When
        repository.addExercise(exercise)
        testDispatcher.scheduler.advanceUntilIdle() // Ensure coroutine completes
        // Then
        verify(mockDao).insertExercise(exercise)
    }

    @Test
    fun exerciseRepository_DeleteExercise_CallDaoDeleteExercise() = runTest {
        // When
        repository.deleteExercise(exercise)
        testDispatcher.scheduler.advanceUntilIdle() // Ensure coroutine completes
        // Then
        verify(mockDao).deleteExercise(exercise)
    }

    @Test
    fun exerciseRepository_GetAllExercises_CallDaoGetAllExercises() = runTest {
        // When
        repository.getAllExercises()
        testDispatcher.scheduler.advanceUntilIdle() // Ensure coroutine completes
        // Then
        verify(mockDao).getAllExercises()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        // Clean up any resources if needed
        Dispatchers.resetMain()
    }
}