package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.UserDao
import com.openclassrooms.arista.domain.model.User
import javax.inject.Inject

//class UserRepository(private val apiService: FakeApiService = FakeApiService()) {
//
//    // Get the current user
//    var user: User
//        get() = apiService.user
//        // Set or update the user
//        set(user) {
//            apiService.user = user
//        }
//}
class UserRepository @Inject constructor(private val userDao: UserDao) {
    // Insert an user
    suspend fun insertUser(user: User) {userDao.insertUser(user)}
    // Get the current user
    suspend fun getUser(): User {return userDao.getUser()}
}