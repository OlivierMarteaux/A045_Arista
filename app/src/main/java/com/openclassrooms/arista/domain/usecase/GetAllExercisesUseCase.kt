package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for retrieving all exercises.
 *
 * @property exerciseRepository Repository to perform exercise data operations.
 */
class GetAllExercisesUseCase @Inject constructor(private val exerciseRepository: ExerciseRepository) {
    /**
     * Executes the use case to get all exercises.
     *
     * @return A [Flow] emitting lists of [Exercise] objects.
     */
    fun execute(): Flow<List<Exercise>> {
        return exerciseRepository.getAllExercises()
    }
}