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

/**
 * ViewModel to manage the UI state and actions related to [Exercise] entities.
 *
 * @property getAllExercisesUseCase Use case to retrieve all exercises as a flow.
 * @property addNewExerciseUseCase Use case to add a new exercise.
 * @property deleteExerciseUseCase Use case to delete an existing exercise.
 */
@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val getAllExercisesUseCase: GetAllExercisesUseCase,
    private val addNewExerciseUseCase: AddNewExerciseUseCase,
    private val deleteExerciseUseCase: DeleteExerciseUseCase
) : ViewModel() {
    /** StateFlow holding the current list of exercises for UI observation. */
    private val _exercisesFlow = MutableStateFlow<List<Exercise>>(emptyList())
    val exercisesFlow: StateFlow<List<Exercise>> = _exercisesFlow.asStateFlow()

    /**
     * Sample initial exercise data for seeding or testing purposes.
     */
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
        // Load all exercises from the database on initialization
        viewModelScope.launch { loadAllExercises()}
    }

    /**
     * Deletes the specified [exercise] and reloads the exercise list.
     *
     * @param exercise The exercise to delete.
     */
    fun deleteExercise(exercise: Exercise) {
        viewModelScope.launch {
            deleteExerciseUseCase.execute(exercise)
            loadAllExercises()
        }
    }

    /**
     * Loads all exercises from the database and updates the [_exercisesFlow].
     */
    private suspend fun loadAllExercises() {
        getAllExercisesUseCase.execute().collect() {
            _exercisesFlow.value = it
        }
    }

    /**
     * Adds a new [exercise] to the database and reloads the exercise list.
     *
     * @param exercise The new exercise to add.
     */
    fun addNewExercise(exercise: Exercise) {
        viewModelScope.launch {
            addNewExerciseUseCase.execute(exercise)
            loadAllExercises()
        }
    }
}
