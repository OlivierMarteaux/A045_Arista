package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for managing the [User] entity in the local database.
 */
@Dao
interface UserDao {
    /**
     * Inserts a [User] or updates it if it already exists.
     *
     * @param user The [User] entity to insert or update.
     */
    @Upsert // Upsert (Insert or Update) : allow overwriting a line if it already exists
    suspend fun insertUser(user: User)

    /**
     * Returns a flow containing the current [User].
     *
     * @return A [Flow] emitting the current [User], or `null` if not set.
     */
    @Query("SELECT * FROM User")
    fun getUser(): Flow<User?>
}