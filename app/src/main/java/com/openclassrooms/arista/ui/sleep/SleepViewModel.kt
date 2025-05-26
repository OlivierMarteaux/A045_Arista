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

@HiltViewModel
class SleepViewModel @Inject constructor(
    private val addSleepUseCase: AddSleepUseCase,
    private val getAllSleepsUseCase: GetAllSleepsUseCase
) :
    ViewModel() {
    private val _sleeps = MutableStateFlow<List<Sleep>>(emptyList())
    val sleeps: StateFlow<List<Sleep>> = _sleeps.asStateFlow()

    private val sleepData = listOf(
        Sleep(1, LocalDateTime.now().minusDays(1), 7, 8),
        Sleep(2, LocalDateTime.now().minusDays(2), 6, 5),
        Sleep(3, LocalDateTime.now().minusDays(3), 8, 9)
    )

    private suspend fun getAllSleeps() {
        getAllSleepsUseCase.execute().collect(){ _sleeps.value = it }
    }

    init {
        viewModelScope.launch {
//            // Add some sleep data for initial database creation:
//            sleepData.forEach { addSleepUseCase.execute(it) }
            // Collect sleep data from database:
            getAllSleeps()
        }
    }
}