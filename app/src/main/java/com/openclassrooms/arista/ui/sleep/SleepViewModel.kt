package com.openclassrooms.arista.ui.sleep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.usecase.AddSleepUseCase
import com.openclassrooms.arista.domain.usecase.GetAllSleepsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

/**
 * ViewModel to manage UI-related data and operations for [Sleep] records.
 *
 * @property addSleepUseCase Use case for inserting new sleep records.
 * @property getAllSleepsUseCase Use case for retrieving all sleep records as a flow.
 */
@HiltViewModel
class SleepViewModel @Inject constructor(
    private val addSleepUseCase: AddSleepUseCase,
    private val getAllSleepsUseCase: GetAllSleepsUseCase
) :
    ViewModel() {
    /** StateFlow holding the current list of sleep records for UI observation. */
    private val _sleeps = MutableStateFlow<List<Sleep>>(emptyList())
    val sleeps: StateFlow<List<Sleep>> = _sleeps.asStateFlow()

    /**
     * Sample initial sleep data for seeding or testing purposes.
     */
    private val sleepData = listOf(
        Sleep(1, LocalDateTime.now().minusDays(1), 7, 8),
        Sleep(2, LocalDateTime.now().minusDays(2), 6, 5),
        Sleep(3, LocalDateTime.now().minusDays(3), 8, 9)
    )

    /**
     * Loads all sleep records from the database and updates the [_sleeps] StateFlow.
     */
    private suspend fun getAllSleeps() {
        getAllSleepsUseCase.execute().collect(){ _sleeps.value = it }
    }

    init {
        viewModelScope.launch {
//            // Add some sleep data for initial database creation:
//            sleepData.forEach { addSleepUseCase.execute(it) }
            // Load sleep data from the database on initialization
            getAllSleeps()
        }
    }
}