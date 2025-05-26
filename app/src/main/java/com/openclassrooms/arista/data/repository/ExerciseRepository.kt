package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.ExerciseDao
import com.openclassrooms.arista.domain.model.Exercise
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExerciseRepository @Inject constructor (private val exerciseDao: ExerciseDao) {

    // Get all exercises
    fun getAllExercises(): Flow<List<Exercise>> = exerciseDao.getAllExercises()

    // Add a new exercise
    suspend fun addExercise(exercise: Exercise) { exerciseDao.insertExercise(exercise) }

    // Delete an exercise
    suspend fun deleteExercise(exercise: Exercise) { exerciseDao.deleteExercise(exercise) }
}