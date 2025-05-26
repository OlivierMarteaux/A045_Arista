package com.openclassrooms.arista.data.repository

import android.util.Log
import com.openclassrooms.arista.data.dao.ExerciseDao
import com.openclassrooms.arista.domain.model.Exercise
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

/**
 * Repository for managing exercise-related operations.
 *
 * Acts as an abstraction layer over [ExerciseDao] to handle data access and error logging.
 *
 * @property exerciseDao The DAO used to access exercise data from the local database.
 */
class ExerciseRepository @Inject constructor (private val exerciseDao: ExerciseDao) {

    /**
     * Retrieves a flow of all [Exercise] entities.
     *
     * @return A [Flow] emitting the list of exercises.
     * Logs an error and returns an empty flow if an exception occurs.
     */
    fun getAllExercises(): Flow<List<Exercise>> =
        try {exerciseDao.getAllExercises()}
        catch (e: Exception) { Log.e("OM:ExerciseRepository.getAllExercises", e.message.toString()); emptyFlow()}

    /**
     * Inserts a new [Exercise] or updates it if it already exists.
     *
     * @param exercise The exercise to add.
     * Logs an error if an exception occurs.
     */
    suspend fun addExercise(exercise: Exercise) =
        try {exerciseDao.insertExercise(exercise)}
        catch (e: Exception) { Log.e("OM:ExerciseRepository.addExercise", e.message.toString())}

    /**
     * Deletes the specified [Exercise] from the database.
     *
     * @param exercise The exercise to delete.
     * Logs an error if an exception occurs.
     */
    suspend fun deleteExercise(exercise: Exercise) =
        try { exerciseDao.deleteExercise(exercise)}
        catch (e: Exception) { Log.e("OM:ExerciseRepository.deleteExercise", e.message.toString())}
}