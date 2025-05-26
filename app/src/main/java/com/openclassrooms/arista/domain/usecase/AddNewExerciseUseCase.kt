package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import javax.inject.Inject

/**
 * Use case for adding a new exercise.
 *
 * @property exerciseRepository Repository to perform exercise data operations.
 */
class AddNewExerciseUseCase @Inject constructor(private val exerciseRepository: ExerciseRepository) {
    /**
     * Executes the use case to add a new exercise.
     *
     * @param exercise The exercise to add.
     */
    suspend fun execute(exercise: Exercise) {
        exerciseRepository.addExercise(exercise)
    }
}