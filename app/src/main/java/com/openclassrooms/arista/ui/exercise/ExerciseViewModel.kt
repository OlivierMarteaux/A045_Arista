package com.openclassrooms.arista.ui.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import com.openclassrooms.arista.domain.usecase.AddNewExerciseUseCase
import com.openclassrooms.arista.domain.usecase.DeleteExerciseUseCase
import com.openclassrooms.arista.domain.usecase.GetAllExercisesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val getAllExercisesUseCase: GetAllExercisesUseCase,
    private val addNewExerciseUseCase: AddNewExerciseUseCase,
    private val deleteExerciseUseCase: DeleteExerciseUseCase
) : ViewModel() {
    private val _exercisesFlow = MutableStateFlow<List<Exercise>>(emptyList())
    val exercisesFlow: StateFlow<List<Exercise>> = _exercisesFlow.asStateFlow()

    private val exerciseData = mutableListOf(
        Exercise(1, LocalDateTime.now().minusHours(5), 30, ExerciseCategory.Running, 7),
        Exercise(
            2,
            LocalDateTime.now().minusDays(1).minusHours(3),
            45,
            ExerciseCategory.Swimming,
            6
        ),
        Exercise(
            3,
            LocalDateTime.now().minusDays(2).minusHours(4),
            60,
            ExerciseCategory.Football,
            8
        )
    )

    init {
//        // Add some exercices for initial database creation:
//        exerciseData.forEach {addNewExercise(it)}
        // Load all exercises from the database:
        viewModelScope.launch { loadAllExercises()}
    }

    fun deleteExercise(exercise: Exercise) {
        viewModelScope.launch {
            deleteExerciseUseCase.execute(exercise)
            loadAllExercises()
        }
    }

    private suspend fun loadAllExercises() {
        getAllExercisesUseCase.execute().collect() {
            _exercisesFlow.value = it
        }
    }

    fun addNewExercise(exercise: Exercise) {
        viewModelScope.launch {
            addNewExerciseUseCase.execute(exercise)
            loadAllExercises()
        }
    }
}
