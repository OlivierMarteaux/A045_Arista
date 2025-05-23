package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.openclassrooms.arista.domain.model.Exercise
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Upsert // Upsert (Insert or Update) : allow overwriting a line if it already exists
    suspend fun insertExercise(exercise: Exercise)
    @Delete
    suspend fun deleteExercise(exercise: Exercise)
    @Query("SELECT * FROM Exercise WHERE id = :id")
    suspend fun getExerciseById(id: Int): Exercise?
    @Query("SELECT * FROM Exercise")
    fun getAllExercises(): Flow<List<Exercise>>
}