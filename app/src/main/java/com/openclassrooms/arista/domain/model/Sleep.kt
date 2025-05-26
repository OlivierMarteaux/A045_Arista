package com.openclassrooms.arista.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

/**
 * Entity class representing a sleep record.
 *
 * Stored in the "sleep" table of the Room database.
 *
 * @property id Auto-generated unique identifier for the sleep record.
 * @property startTime The start time of the sleep session.
 * @property duration The duration of sleep in hours.
 * @property quality A user-rated quality of sleep (e.g., 1–10).
 */
@Entity(tableName = "sleep")
data class Sleep(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @JvmField var startTime: LocalDateTime,
    var duration: Int,
    var quality: Int
)
