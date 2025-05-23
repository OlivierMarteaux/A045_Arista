package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.openclassrooms.arista.domain.model.User

@Dao
interface UserDao {
//    @Insert
//    suspend fun insertUser(user: User)
    @Upsert // Upsert (Insert or Update) : allow overwriting a line if it already exists
    suspend fun insertUser(user: User)
    @Query("SELECT * FROM User")
    suspend fun getUser(): User?
}