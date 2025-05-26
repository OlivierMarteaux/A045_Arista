package com.openclassrooms.arista.ui.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.openclassrooms.arista.R
import com.openclassrooms.arista.databinding.FragmentExerciseBinding
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDateTime

interface DeleteExerciseInterface {
    fun deleteExercise(exercise: Exercise?)
}

/**
 * Fragment displaying a list of exercises with functionality to add and delete exercises.
 *
 * Implements [DeleteExerciseInterface] to handle exercise deletion events from the adapter.
 *
 * Features:
 * - Observes the [ExerciseViewModel] for exercise data updates.
 * - Displays exercises in a RecyclerView using [ExerciseAdapter].
 * - Provides a Floating Action Button (FAB) to open a dialog for adding new exercises.
 * - Validates user input before adding new exercises.
 */
@AndroidEntryPoint
class ExerciseFragment : Fragment(), DeleteExerciseInterface {

    private var _binding: FragmentExerciseBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ExerciseViewModel by viewModels()
    private lateinit var exerciseAdapter: ExerciseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupFab()
        observeExercises()
    }

    /**
     * Sets up the RecyclerView with [ExerciseAdapter] and a vertical linear layout manager.
     */
    private fun setupRecyclerView() {
        exerciseAdapter = ExerciseAdapter(this)
        binding.exerciseRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.exerciseRecyclerview.adapter = exerciseAdapter
    }

    /**
     * Observes the exercise list from the ViewModel and submits updates to the adapter.
     */
    private fun observeExercises() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.exercisesFlow.collect { exercises ->
                exerciseAdapter.submitList(exercises)
            }
        }
    }

    /**
     * Sets up the Floating Action Button to open the add exercise dialog.
     */
    private fun setupFab() {
        binding.fab.setOnClickListener { showAddExerciseDialog() }
    }

    /**
     * Displays a dialog allowing the user to add a new exercise.
     * Validates inputs before adding.
     */
    private fun showAddExerciseDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_exercise, null)
        setupDialogViews(dialogView).also {
            AlertDialog.Builder(requireContext())
                .setView(dialogView)
                .setTitle(R.string.add_new_exercise)
                .setPositiveButton(R.string.add) { _, _ -> addExercise(it) }
                .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

    /**
     * Initializes dialog input views: duration EditText, category Spinner, and intensity EditText.
     *
     * @param dialogView The dialog's inflated view.
     * @return A Triple containing the duration EditText, category Spinner, and intensity EditText.
     */
    private fun setupDialogViews(dialogView: View): Triple<EditText, Spinner, EditText> {
        val durationEditText = dialogView.findViewById<EditText>(R.id.durationEditText)
        val categorySpinner = dialogView.findViewById<Spinner>(R.id.categorySpinner).apply {
            adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                ExerciseCategory.values()
            ).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        }
        val intensityEditText = dialogView.findViewById<EditText>(R.id.intensityEditText)
        return Triple(durationEditText, categorySpinner, intensityEditText)
    }

    /**
     * Validates inputs and adds a new [Exercise] via the ViewModel.
     *
     * @param views Triple of duration EditText, category Spinner, and intensity EditText.
     */
    private fun addExercise(views: Triple<EditText, Spinner, EditText>) {
        val (durationEditText, categorySpinner, intensityEditText) = views

        val durationStr = durationEditText.text.toString().trim()
        val intensityStr = intensityEditText.text.toString().trim()

        val isDurationValid = validateDuration(durationStr)
        val isIntensityValid = validateIntensity(intensityStr)

        if (!isDurationValid || !isIntensityValid) return

        val duration = durationStr.toInt()
        val intensity = intensityStr.toInt()
        val category = categorySpinner.selectedItem as ExerciseCategory

        val newExercise =
            Exercise(System.currentTimeMillis(), LocalDateTime.now(), duration, category, intensity)
        viewModel.addNewExercise(newExercise)
    }

    /**
     * Validates the duration input.
     *
     * @param duration The input duration string.
     * @return True if valid, false otherwise (shows Toast on invalid input).
     */
    private fun validateDuration(duration: String): Boolean {
        if (duration.isBlank()) {
            Toast.makeText(requireContext(), R.string.fill_all_fields, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    /**
     * Validates the intensity input, ensuring it is a number between 1 and 10.
     *
     * @param intensity The input intensity string.
     * @return True if valid, false otherwise (shows Toast on invalid input).
     */
    private fun validateIntensity(intensity: String): Boolean {
        if (intensity.isBlank()) {
            Toast.makeText(requireContext(), R.string.fill_all_fields, Toast.LENGTH_SHORT).show()
            return false
        }

        return try {
            val intensityValue = intensity.toInt()
            if (intensityValue !in 1..10) {
                Toast.makeText(
                    requireContext(),
                    R.string.intensity_should_be_between_1_and_10,
                    Toast.LENGTH_SHORT
                ).show()
                false
            } else {
                true
            }
        } catch (e: NumberFormatException) {
            Toast.makeText(
                requireContext(),
                R.string.invalid_input_please_enter_valid_numbers,
                Toast.LENGTH_SHORT
            ).show()
            false
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Called by the [ExerciseAdapter] when the user requests to delete an exercise.
     *
     * @param exercise The [Exercise] to delete.
     */
    override fun deleteExercise(exercise: Exercise?) {
        exercise?.let { viewModel.deleteExercise(it) }
    }
}
