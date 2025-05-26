package com.openclassrooms.arista

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.openclassrooms.arista.data.AristaDatabase
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class ExerciseDaoTest {
    private lateinit var database: AristaDatabase

    @Before
    fun createDb() {
        database = Room
            .inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                AristaDatabase::class.java
            )
            .allowMainThreadQueries()
            .build()
    }
    private val fixedTime = LocalDateTime.of(2025, 5, 22, 18, 0, 0)
    private val exercise1 = Exercise(
        id = 1,
        startTime = fixedTime,
        duration = 30,
        category = ExerciseCategory.Running,
        intensity = 7
    )
    private val exercise2 = Exercise(
        id = 2,
        startTime = fixedTime,
        duration = 45,
        category = ExerciseCategory.Swimming,
        intensity = 6
    )
    private val exercise3 = Exercise(
        id = 3,
        startTime = fixedTime,
        duration = 60,
        category = ExerciseCategory.Football,
        intensity = 8
    )
    private val exerciseList = listOf(exercise1, exercise2, exercise3)

    @Test
    fun exerciseDao_InsertExercise_ReturnsCorrectExercise() = runTest {
        // Given an exercise is inserted in the database
        database.exerciseDao().insertExercise(exercise1)
        // When the exercise is retrieved from the database
        val retrievedExercise = database.exerciseDao().getExerciseById(exercise1.id!!.toInt())
        // Then the retrieved exercise is the same as the inserted exercise
        assertEquals(exercise1, retrievedExercise)
    }

    @Test
    fun exerciseDao_DeleteExercise_ReturnsNull() = runTest {
        // Given exercise is inserted in the database
        database.exerciseDao().insertExercise(exercise1)
        // When the exercise is deleted from the database
        database.exerciseDao().deleteExercise(exercise1)
        // Then an empty list is returned
        assertNull(database.exerciseDao().getExerciseById(exercise1.id!!.toInt()))
    }

    @Test
    fun exerciseDao_GetAllExercises_ReturnsCorrectExercisesList() = runTest {
        // Given exercises are inserted in the database
        exerciseList.forEach{database.exerciseDao().insertExercise(it)}
        // When the exercise is retrieved from the database
        database.exerciseDao().getAllExercises().test{
            val retrievedExercises = awaitItem()
            // Then the retrieved exercise is the same as the inserted exercise
            assertEquals(exerciseList, retrievedExercises)
            cancel()
        }
    }

    @After
    // close the database instance after test
    fun closeDb(){
        database.close()
    }
}