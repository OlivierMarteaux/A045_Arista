package com.openclassrooms.arista.di

import android.content.Context
import androidx.room.Room
import com.openclassrooms.arista.data.AristaDatabase
import com.openclassrooms.arista.data.dao.ExerciseDao
import com.openclassrooms.arista.data.dao.SleepDao
import com.openclassrooms.arista.data.dao.UserDao
import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.data.repository.SleepRepository
import com.openclassrooms.arista.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

/**
 * Hilt module that provides application-level dependencies for the Arista app.
 *
 * Annotated with [@Module] to declare dependency providers.
 * Annotated with [@InstallIn(SingletonComponent::class)] to specify the application-wide scope.
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    /**
     * Provides a [CoroutineScope] for background operations.
     *
     * This scope uses [Dispatchers.IO] and a [SupervisorJob] to ensure child coroutines can fail independently.
     *
     * @return A singleton [CoroutineScope] used throughout the app.
     */
    @Provides
    @Singleton
    fun provideCoroutineScope(): CoroutineScope = CoroutineScope(SupervisorJob() +
    Dispatchers.IO)
    /**
     * Provides the singleton instance of [AristaDatabase].
     *
     * @param context The application context (automatically injected by Hilt).
     * @param coroutineScope The coroutine scope for database initialization.
     * @return A singleton [AristaDatabase] instance.
     */
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        coroutineScope: CoroutineScope
    ): AristaDatabase {
        return AristaDatabase.getInstance(context, coroutineScope)
    }
    /**
     * Provides the [UserDao] instance from the [AristaDatabase].
     *
     * @param database The [AristaDatabase] instance.
     * @return The [UserDao] for accessing user data.
     */
    @Provides
    @Singleton
    fun provideUserDao(database: AristaDatabase) = database.userDao()
    /**
     * Provides the [SleepDao] instance from the [AristaDatabase].
     *
     * @param database The [AristaDatabase] instance.
     * @return The [SleepDao] for accessing sleep data.
     */
    @Provides
    @Singleton
    fun provideSleepDao(database: AristaDatabase) = database.sleepDao()
    /**
     * Provides the [ExerciseDao] instance from the [AristaDatabase].
     *
     * @param database The [AristaDatabase] instance.
     * @return The [ExerciseDao] for accessing exercise data.
     */
    @Provides
    @Singleton
    fun provideExerciseDao(database: AristaDatabase) = database.exerciseDao()
    /**
     * Provides the [UserRepository] instance.
     *
     * @param userDao The DAO used to access user data.
     * @return The [UserRepository] instance.
     */
    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepository(userDao)
    }
    /**
     * Provides the [SleepRepository] instance.
     *
     * @param sleepDao The DAO used to access sleep data.
     * @return The [SleepRepository] instance.
     */
    @Provides
    @Singleton
    fun provideSleepRepository(sleepDao: SleepDao): SleepRepository {
        return SleepRepository(sleepDao)
    }
    /**
     * Provides the [ExerciseRepository] instance.
     *
     * @param exerciseDao The DAO used to access exercise data.
     * @return The [ExerciseRepository] instance.
     */
    @Provides
    @Singleton
    fun provideExerciseRepository(exerciseDao: ExerciseDao): ExerciseRepository {
        return ExerciseRepository(exerciseDao)
    }
}