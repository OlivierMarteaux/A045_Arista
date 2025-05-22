package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.openclassrooms.arista.domain.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)
    @Query("SELECT * FROM User")
    suspend fun getUser(): User
}