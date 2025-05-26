package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Upsert // Upsert (Insert or Update) : allow overwriting a line if it already exists
    suspend fun insertUser(user: User)
    @Query("SELECT * FROM User")
    fun getUser(): Flow<User?>
}