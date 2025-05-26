package com.openclassrooms.arista.usecase

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import com.openclassrooms.arista.domain.usecase.AddNewExerciseUseCase
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

class AddNewExerciseUseCaseTest {
    private val mockRepository: ExerciseRepository = mock()
    private lateinit var useCase: AddNewExerciseUseCase
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        useCase = AddNewExerciseUseCase(mockRepository)
    }
    private val fixedTime = LocalDateTime.of(2025, 5, 22, 18, 0, 0)
    private val exercise1 = Exercise(
        id = 1,
        startTime = fixedTime,
        duration = 30,
        category = ExerciseCategory.Running,
        intensity = 7
    )
    private val exercise2 = Exercise(
        id = 2,
        startTime = fixedTime,
        duration = 45,
        category = ExerciseCategory.Swimming,
        intensity = 6
    )
    private val exercise3 = Exercise(
        id = 3,
        startTime = fixedTime,
        duration = 60,
        category = ExerciseCategory.Football,
        intensity = 8
    )
    private val exerciseList = listOf(exercise1, exercise2, exercise3)

    @Test
    fun addNewExerciseUseCase_Execute_CallRepositoryAddExercise() = runTest {
        // Given
        val exercise = exercise1

        // When
        useCase.execute(exercise)
        testDispatcher.scheduler.advanceUntilIdle() // Ensure coroutine completes

        // Then
        verify(mockRepository).addExercise(exercise)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        // Clean up any resources if needed
        Dispatchers.resetMain()
    }
}