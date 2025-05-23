package com.openclassrooms.arista.data

import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class AristaDatabaseCallback(private val database: AristaDatabase) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        Log.d("RoomCallback", "onCreate called")
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            val userDao = database.userDao()
            val exerciseDao = database.exerciseDao()
            val sleepDao = database.sleepDao()
            userDao.insertUser(userData)
            sleepData.forEach { sleepDao.insertSleep(it) }
            exerciseData.forEach { exerciseDao.insertExercise(it) }
        }
    }

    private val userData = User(1, "User", "user@example.com")

    private val sleepData = listOf(
        Sleep(1, LocalDateTime.now().minusDays(1), 7, 8),
        Sleep(2, LocalDateTime.now().minusDays(2), 6, 5),
        Sleep(3, LocalDateTime.now().minusDays(3), 8, 9)
    )

    private val exerciseData = mutableListOf(
        Exercise(1, LocalDateTime.now().minusHours(5), 30, ExerciseCategory.Running, 7),
        Exercise(
            2,
            LocalDateTime.now().minusDays(1).minusHours(3),
            45,
            ExerciseCategory.Swimming,
            6
        ),
        Exercise(
            3,
            LocalDateTime.now().minusDays(2).minusHours(4),
            60,
            ExerciseCategory.Football,
            8
        )
    )
}