package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for retrieving the current user.
 *
 * @property userRepository Repository to perform user data operations.
 */
class GetUserUsecase @Inject constructor(private val userRepository: UserRepository) {
    /**
     * Executes the use case to get the current user.
     *
     * @return A [Flow] emitting the current [User] or `null` if none exists.
     */
    fun execute(): Flow<User?> {
        return userRepository.getUser()
    }
}