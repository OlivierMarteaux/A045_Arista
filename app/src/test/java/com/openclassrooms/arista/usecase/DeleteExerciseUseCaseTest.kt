package com.openclassrooms.arista.usecase

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import com.openclassrooms.arista.domain.usecase.DeleteExerciseUseCase
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

class DeleteExerciseUseCaseTest {
    private val mockRepository: ExerciseRepository = mock()
    private lateinit var useCase: DeleteExerciseUseCase
    private val testDispatcher = StandardTestDispatcher()
    private val fixedTime = LocalDateTime.of(2025, 5, 22, 18, 0, 0)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        useCase = DeleteExerciseUseCase(mockRepository)
    }

    @Test
    fun deleteExerciseUseCase_Execute_CallRepositoryDeleteExercise() = runTest {
        // Given
        val exercise = Exercise(
            id = 1,
            startTime = fixedTime,
            duration = 30,
            category = ExerciseCategory.Running,
            intensity = 7
        )

        // When
        useCase.execute(exercise)
        testDispatcher.scheduler.advanceUntilIdle() // Ensure coroutine completes

        // Then
        verify(mockRepository).deleteExercise(exercise)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        // Clean up any resources if needed
        Dispatchers.resetMain()
    }
}