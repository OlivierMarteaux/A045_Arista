package com.openclassrooms.arista.usecase

import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.domain.usecase.AddUserUseCase
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

class AddUserUseCaseTest {
    private val mockRepository: UserRepository = mock()
    private lateinit var useCase: AddUserUseCase
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        useCase = AddUserUseCase(mockRepository)
    }

    @Test
    fun addUserUseCase_Execute_CallRepositoryInsertUser() = runTest {
        // Given
        val user = User(1, "test", "test@test.com")

        // When
        useCase.execute(user)
        testDispatcher.scheduler.advanceUntilIdle() // Ensure coroutine completes

        // Then
        verify(mockRepository).insertUser(user)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        // Clean up any resources if needed
        Dispatchers.resetMain()
    }
}