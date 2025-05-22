package com.openclassrooms.arista.domain.model

import androidx.room.TypeConverter

class ExerciseCategoryConverter {
    @TypeConverter
    fun fromExerciseCategory(category: ExerciseCategory): String {
        return category.name
    }
    @TypeConverter
    fun toExerciseCategory(name: String): ExerciseCategory {
        return ExerciseCategory.valueOf(name)
    }
}