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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AristaDatabase {
//        return Room
//            .databaseBuilder(
//                context,
//                AristaDatabase::class.java,
//                "arista.db")
//            .createFromAsset("database/arista.db")
//            .build()
        return AristaDatabase.getInstance(context)
    }
    @Provides
    @Singleton
    fun provideUserDao(database: AristaDatabase) = database.userDao()
    @Provides
    @Singleton
    fun provideSleepDao(database: AristaDatabase) = database.sleepDao()
    @Provides
    @Singleton
    fun provideExerciseDao(database: AristaDatabase) = database.exerciseDao()

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepository(userDao)
    }

    @Provides
    @Singleton
    fun provideSleepRepository(sleepDao: SleepDao): SleepRepository {
        return SleepRepository(sleepDao)
    }

    @Provides
    @Singleton
    fun provideExerciseRepository(exerciseDao: ExerciseDao): ExerciseRepository {
        return ExerciseRepository(exerciseDao)
    }
}