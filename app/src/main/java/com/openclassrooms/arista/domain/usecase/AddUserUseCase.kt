package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import javax.inject.Inject

/**
 * Use case for adding a new user.
 *
 * @property userRepository Repository to perform user data operations.
 */
class AddUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    /**
     * Executes the use case to add a new user.
     *
     * @param user The user to add.
     */
    suspend fun execute(user: User) {
        userRepository.insertUser(user)
    }
}