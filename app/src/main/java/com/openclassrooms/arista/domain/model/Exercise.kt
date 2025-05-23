package com.openclassrooms.arista.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

import java.time.LocalDateTime

@Entity(
    tableName = "exercise",
)
data class Exercise(
    @PrimaryKey val id: Long? = null,
    var startTime: LocalDateTime,
    var duration: Int,
    var category: ExerciseCategory,
    var intensity: Int,
)