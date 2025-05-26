package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.UserDao
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    // Insert an user
    suspend fun insertUser(user: User) {userDao.insertUser(user)}

    // Get the current user
    fun getUser(): Flow<User?> {return userDao.getUser()}
}