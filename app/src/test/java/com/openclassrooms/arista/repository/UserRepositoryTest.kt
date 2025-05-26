package com.openclassrooms.arista.repository

import com.openclassrooms.arista.data.dao.UserDao
import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import java.time.LocalDateTime

class UserRepositoryTest {
    private val mockDao: UserDao = mock()
    private lateinit var repository: UserRepository
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = UserRepository(mockDao)
    }

    @Test
    fun userRepository_AddUser_CallDaoInsertUser() = runTest {
        // Given
        val user = User(1, "test", "test@test.com")
        // When
        repository.insertUser(user)
        testDispatcher.scheduler.advanceUntilIdle() // Ensure coroutine completes
        // Then
        verify(mockDao).insertUser(user)
    }

    @Test
    fun userRepository_GetAllUsers_CallDaoGetAllUsers() = runTest {
        // When
        repository.getUser()
        testDispatcher.scheduler.advanceUntilIdle() // Ensure coroutine completes
        // Then
        verify(mockDao).getUser()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        // Clean up any resources if needed
        Dispatchers.resetMain()
    }
}