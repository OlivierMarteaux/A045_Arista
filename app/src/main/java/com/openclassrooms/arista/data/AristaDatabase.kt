package com.openclassrooms.arista.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.openclassrooms.arista.data.dao.ExerciseDao
import com.openclassrooms.arista.data.dao.SleepDao
import com.openclassrooms.arista.data.dao.UserDao
import com.openclassrooms.arista.domain.model.DateConverter
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategoryConverter
import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.model.User

@Database(entities = [User::class, Sleep::class, Exercise::class], version = 1, exportSchema = true)
@TypeConverters(DateConverter::class, ExerciseCategoryConverter::class)
abstract class AristaDatabase: RoomDatabase() {

    // a singleton instance of the database
    companion object {
        private var instance: AristaDatabase? = null
        fun getInstance(context: Context): AristaDatabase {
            return instance?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AristaDatabase::class.java,
                    "arista.db"
                )
                    .build()
                    .also { instance = it }
            }
        }
    }

    abstract fun userDao(): UserDao
    abstract fun sleepDao(): SleepDao
    abstract fun exerciseDao(): ExerciseDao
}