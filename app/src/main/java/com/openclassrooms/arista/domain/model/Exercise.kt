package com.openclassrooms.arista.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

import java.time.LocalDateTime

@Entity(
    tableName = "exercise",
//    foreignKeys = [
//        ForeignKey(
//            entity = ExerciseCategory::class,
//            parentColumns = ["id"],
//            childColumns = ["userId"],
//            onDelete = ForeignKey.CASCADE
//        )
//    ]
)
data class Exercise(
    @PrimaryKey val id: Long? = null,
    var startTime: LocalDateTime,
    var duration: Int,
    var category: ExerciseCategory,
    var intensity: Int,
//    val userId: Int
)