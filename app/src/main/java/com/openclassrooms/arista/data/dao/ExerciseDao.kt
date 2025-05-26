package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.openclassrooms.arista.domain.model.Exercise
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for managing [Exercise] entities in the local database.
 */
@Dao
interface ExerciseDao {
    /**
     * Inserts a new [Exercise] or updates it if it already exists.
     *
     * @param exercise The [Exercise] entity to insert or update.
     */
    @Upsert // Upsert (Insert or Update) : allow overwriting a line if it already exists
    suspend fun insertExercise(exercise: Exercise)
    /**
     * Deletes the specified [Exercise] from the database.
     *
     * @param exercise The [Exercise] entity to delete.
     */
    @Delete
    suspend fun deleteExercise(exercise: Exercise)
    /**
     * Retrieves an [Exercise] by its unique ID.
     *
     * @param id The unique identifier of the exercise.
     * @return The [Exercise] entity if found, or `null` otherwise.
     */
    @Query("SELECT * FROM Exercise WHERE id = :id")
    suspend fun getExerciseById(id: Int): Exercise?
    /**
     * Returns a flow of all [Exercise] entities in the database.
     *
     * @return A [Flow] emitting the list of all exercises.
     */
    @Query("SELECT * FROM Exercise")
    fun getAllExercises(): Flow<List<Exercise>>
}