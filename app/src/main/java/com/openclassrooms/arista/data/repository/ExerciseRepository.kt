package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.ExerciseDao
import com.openclassrooms.arista.domain.model.Exercise
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//class ExerciseRepository(private val apiService: FakeApiService = FakeApiService()) {
//
//    // Get all exercises
//    val allExercises: List<Exercise> get() = apiService.getAllExercises()
//
//    // Add a new exercise
//    fun addExercise(exercise: Exercise) {
//        apiService.addExercise(exercise)
//    }
//
//    // Delete an exercise
//    fun deleteExercise(exercise: Exercise) {
//        apiService.deleteExercise(exercise)
//    }
//}

class ExerciseRepository @Inject constructor (private val exerciseDao: ExerciseDao) {

    // Get all exercises
    fun getAllExercises(): Flow<List<Exercise>> = exerciseDao.getAllExercises()

    // Add a new exercise
    suspend fun addExercise(exercise: Exercise) { exerciseDao.insertExercise(exercise) }

    // Delete an exercise
    suspend fun deleteExercise(exercise: Exercise) { exerciseDao.deleteExercise(exercise) }
}