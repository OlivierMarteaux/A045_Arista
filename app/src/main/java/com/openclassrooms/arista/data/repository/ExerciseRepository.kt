package com.openclassrooms.arista.data.repository

import android.util.Log
import com.openclassrooms.arista.data.dao.ExerciseDao
import com.openclassrooms.arista.domain.model.Exercise
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class ExerciseRepository @Inject constructor (private val exerciseDao: ExerciseDao) {

    // Get all exercises
    fun getAllExercises(): Flow<List<Exercise>> =
        try {exerciseDao.getAllExercises()}
        catch (e: Exception) { Log.e("OM:ExerciseRepository.getAllExercises", e.message.toString()); emptyFlow()}

    // Add a new exercise
    suspend fun addExercise(exercise: Exercise) =
        try {exerciseDao.insertExercise(exercise)}
        catch (e: Exception) { Log.e("OM:ExerciseRepository.addExercise", e.message.toString())}

    // Delete an exercise
    suspend fun deleteExercise(exercise: Exercise) =
        try { exerciseDao.deleteExercise(exercise)}
        catch (e: Exception) { Log.e("OM:ExerciseRepository.deleteExercise", e.message.toString())}
}