package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.SleepRepository
import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for retrieving all sleep records.
 *
 * @property sleepRepository Repository to perform sleep data operations.
 */
class GetAllSleepsUseCase @Inject constructor(private val sleepRepository: SleepRepository) {
    /**
     * Executes the use case to get all sleep records.
     *
     * @return A [Flow] emitting lists of [Sleep] objects.
     */
    fun execute(): Flow<List<Sleep>> {
        return sleepRepository.getAllSleeps()
    }
}