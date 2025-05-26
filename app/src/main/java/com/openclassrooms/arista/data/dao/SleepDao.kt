package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for managing [Sleep] records in the local database.
 */
@Dao
interface SleepDao {
    /**
     * Inserts a new [Sleep] record or updates it if it already exists.
     *
     * @param sleep The [Sleep] entity to insert or update.
     */
    @Upsert // Upsert (Insert or Update) : allow overwriting a line if it already exists
    suspend fun insertSleep(sleep: Sleep)
    /**
     * Retrieves a [Sleep] record by its unique ID.
     *
     * @param id The unique identifier of the sleep record.
     * @return The [Sleep] entity if found, or `null` otherwise.
     */
    @Query("SELECT * FROM Sleep WHERE id = :id")
    suspend fun getSleepById(id: Int): Sleep?
    /**
     * Returns a flow of all [Sleep] records in the database.
     *
     * @return A [Flow] emitting the list of all sleep entries.
     */
    @Query("SELECT * FROM Sleep")
    fun getAllSleeps(): Flow<List<Sleep>>
}