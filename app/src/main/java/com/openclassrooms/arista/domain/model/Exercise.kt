package com.openclassrooms.arista.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

import java.time.LocalDateTime

/**
 * Entity class representing a physical exercise record.
 *
 * Stored in the "exercise" table of the Room database.
 *
 * @property id Optional unique identifier for the exercise.
 * @property startTime The start time of the exercise.
 * @property duration The duration of the exercise in minutes.
 * @property category The type of exercise performed.
 * @property intensity A user-rated intensity of the exercise (e.g., 1–10).
 */
@Entity(tableName = "exercise",)
data class Exercise(
    @PrimaryKey val id: Long? = null,
    var startTime: LocalDateTime,
    var duration: Int,
    var category: ExerciseCategory,
    var intensity: Int,
)