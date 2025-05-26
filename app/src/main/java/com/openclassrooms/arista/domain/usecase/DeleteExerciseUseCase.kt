package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import javax.inject.Inject

/**
 * Use case for deleting an existing exercise.
 *
 * @property exerciseRepository Repository to perform exercise data operations.
 */
class DeleteExerciseUseCase @Inject constructor(private val exerciseRepository: ExerciseRepository) {
    /**
     * Executes the use case to delete an exercise.
     *
     * @param exercise The exercise to delete.
     */
    suspend fun execute(exercise: Exercise) {
        exerciseRepository.deleteExercise(exercise)
    }
}