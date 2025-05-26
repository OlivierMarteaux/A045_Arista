package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.SleepRepository
import com.openclassrooms.arista.domain.model.Sleep
import javax.inject.Inject

/**
 * Use case for adding a new sleep record.
 *
 * @property sleepRepository Repository to perform sleep data operations.
 */
class AddSleepUseCase @Inject constructor(private val sleepRepository: SleepRepository) {
    /**
     * Executes the use case to add a new sleep record.
     *
     * @param sleep The sleep record to add.
     */
    suspend fun execute(sleep: Sleep) {
        sleepRepository.insertSleep(sleep)
    }
}