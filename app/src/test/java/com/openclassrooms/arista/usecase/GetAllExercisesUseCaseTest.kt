package com.openclassrooms.arista.usecase

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import com.openclassrooms.arista.domain.usecase.AddNewExerciseUseCase
import com.openclassrooms.arista.domain.usecase.GetAllExercisesUseCase
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

class GetAllExercisesUseCaseTest {
    private val mockRepository: ExerciseRepository = mock()
    private lateinit var useCase: GetAllExercisesUseCase
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        useCase = GetAllExercisesUseCase(mockRepository)
    }

    @Test
    fun addNewExerciseUseCase_Execute_CallRepositoryAddExercise() = runTest {
        // When
        useCase.execute()
        testDispatcher.scheduler.advanceUntilIdle() // Ensure coroutine completes

        // Then
        verify(mockRepository).getAllExercises()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        // Clean up any resources if needed
        Dispatchers.resetMain()
    }
}