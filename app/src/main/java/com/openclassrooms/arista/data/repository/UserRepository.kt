package com.openclassrooms.arista.data.repository

import android.util.Log
import com.openclassrooms.arista.data.dao.UserDao
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    // Insert an user
    suspend fun insertUser(user: User) =
        try {userDao.insertUser(user)}
        catch (e: Exception) { Log.e("OM:UserRepository.insertUser", e.message.toString())}

    // Get the current user
    fun getUser(): Flow<User?> =
        try {userDao.getUser()}
        catch (e: Exception) { Log.e("OM:UserRepository.getUser", e.message.toString()); emptyFlow()}
}