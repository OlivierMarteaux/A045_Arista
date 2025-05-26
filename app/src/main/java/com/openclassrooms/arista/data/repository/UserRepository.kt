package com.openclassrooms.arista.data.repository

import android.util.Log
import com.openclassrooms.arista.data.dao.UserDao
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

/**
 * Repository for managing user-related operations.
 *
 * Provides access to user data via [UserDao], with basic error handling.
 *
 * @property userDao The DAO used to access user data from the local database.
 */
class UserRepository @Inject constructor(private val userDao: UserDao) {

    /**
     * Inserts a [User] or updates it if it already exists.
     *
     * @param user The user to insert.
     * Logs an error if an exception occurs.
     */
    suspend fun insertUser(user: User) =
        try {userDao.insertUser(user)}
        catch (e: Exception) { Log.e("OM:UserRepository.insertUser", e.message.toString())}

    /**
     * Retrieves the current [User] as a flow.
     *
     * @return A [Flow] emitting the user or null if not set.
     * Logs an error and returns an empty flow if an exception occurs.
     */
    fun getUser(): Flow<User?> =
        try {userDao.getUser()}
        catch (e: Exception) { Log.e("OM:UserRepository.getUser", e.message.toString()); emptyFlow()}
}