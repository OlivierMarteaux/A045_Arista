package com.openclassrooms.arista.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.openclassrooms.arista.data.dao.ExerciseDao
import com.openclassrooms.arista.data.dao.SleepDao
import com.openclassrooms.arista.data.dao.UserDao
import com.openclassrooms.arista.domain.model.DateConverter
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import com.openclassrooms.arista.domain.model.ExerciseCategoryConverter
import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

/**
 * The Room database for the Arista app.
 *
 * This database holds entities for [User], [Sleep], and [Exercise], and provides DAOs for accessing them.
 * It also defines a singleton pattern and a callback to populate the database on first creation.
 *
 * Annotated with [@Database] to specify the entities and version.
 * Annotated with [@TypeConverters] to handle custom data types like [LocalDateTime] and [ExerciseCategory].
 */
@Database(entities = [User::class, Sleep::class, Exercise::class], version = 1, exportSchema = true)
@TypeConverters(DateConverter::class, ExerciseCategoryConverter::class)
abstract class AristaDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun sleepDao(): SleepDao
    abstract fun exerciseDao(): ExerciseDao

    /**
     * A [RoomDatabase.Callback] to populate the database with initial data
     * when it is first created.
     *
     * @property scope The [CoroutineScope] used to run the population asynchronously.
     */
    private class AristaDatabaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.userDao(), database.sleepDao(), database.exerciseDao())
                    Log.d("OM:AristaDatabaseCallback", "Database populated")
                }
            }
        }
    }

    // a singleton instance of the database
    companion object {
        /**
         * A volatile singleton instance of [AristaDatabase] to prevent race conditions.
         */
        @Volatile
        private var INSTANCE: AristaDatabase? = null
        /**
         * Returns the singleton instance of [AristaDatabase].
         *
         * @param context The application context.
         * @param coroutineScope The coroutine scope used for initial population.
         * @return The singleton [AristaDatabase] instance.
         */
        fun getInstance(context: Context, coroutineScope: CoroutineScope): AristaDatabase {
//            context.deleteDatabase("app_database") // ✅ Ensure onCreate runs in debug
            return INSTANCE?: synchronized(this) {

            val instance = Room
                .databaseBuilder(
                        context.applicationContext,
                        AristaDatabase::class.java,
                        "arista.db"
                    )
                .addCallback(AristaDatabaseCallback(CoroutineScope(Dispatchers.IO)))
                .build()
                INSTANCE = instance
                return instance
            }
        }
        /**
         * Populates the database with sample data on first launch.
         *
         * @param userDao DAO for inserting user data.
         * @param sleepDao DAO for inserting sleep records.
         * @param exerciseDao DAO for inserting exercise entries.
         */
        suspend fun populateDatabase(userDao: UserDao, sleepDao: SleepDao, exerciseDao: ExerciseDao) {
            val userData = User(1, "User", "user@example.com")
            val sleepData = listOf(
                Sleep(1, LocalDateTime.now().minusDays(1), 7, 8),
                Sleep(2, LocalDateTime.now().minusDays(2), 6, 5),
                Sleep(3, LocalDateTime.now().minusDays(3), 8, 9)
            )
            val exerciseData = mutableListOf(
                Exercise(1, LocalDateTime.now().minusHours(5), 30, ExerciseCategory.Running, 7),
                Exercise(2, LocalDateTime.now().minusDays(1).minusHours(3), 45, ExerciseCategory.Swimming, 6),
                Exercise(3, LocalDateTime.now().minusDays(2).minusHours(4), 60, ExerciseCategory.Football, 8)
            )
            userDao.insertUser(userData)
            sleepData.forEach { sleepDao.insertSleep(it) }
            exerciseData.forEach { exerciseDao.insertExercise(it) }
        }
    }
}