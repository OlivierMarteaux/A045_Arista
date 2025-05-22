package com.openclassrooms.arista.di

import android.content.Context
import androidx.room.Room
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
//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext context: Context): AristaDatabase {
//        return Room.databaseBuilder(
//            context,
//            AristaDatabase::class.java,
//            "arista.db"
//        ).build()
//    }
//    @Provides
//    @Singleton
//    fun provideUserDao(database: AristaDatabase) = database.userDao()
//    @Provides
//    @Singleton
//    fun provideSleepDao(database: AristaDatabase) = database.sleepDao()
//    @Provides
//    @Singleton
//    fun provideExerciseDao(database: AristaDatabase) = database.exerciseDao()

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository {
        return UserRepository()
    }

    @Provides
    @Singleton
    fun provideSleepRepository(): SleepRepository {
        return SleepRepository()
    }

    @Provides
    @Singleton
    fun provideExerciseRepository(): ExerciseRepository {
        return ExerciseRepository()
    }
}