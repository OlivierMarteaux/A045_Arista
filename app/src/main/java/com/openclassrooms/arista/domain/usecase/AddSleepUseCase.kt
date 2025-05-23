package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.SleepRepository
import com.openclassrooms.arista.domain.model.Sleep
import javax.inject.Inject

// Only used for DB creation
class AddSleepUseCase @Inject constructor(private val sleepRepository: SleepRepository) {
    suspend fun execute(sleep: Sleep) {
        sleepRepository.insertSleep(sleep)
    }
}