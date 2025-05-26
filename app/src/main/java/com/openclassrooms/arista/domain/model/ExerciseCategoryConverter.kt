package com.openclassrooms.arista.domain.model

import androidx.room.TypeConverter

/**
 * Type converter for storing and retrieving [ExerciseCategory] values as [String]s in the database.
 */
class ExerciseCategoryConverter {
    /**
     * Converts an [ExerciseCategory] to its [String] representation.
     *
     * @param category The exercise category.
     * @return The name of the enum value.
     */
    @TypeConverter
    fun fromExerciseCategory(category: ExerciseCategory): String {
        return category.name
    }
    /**
     * Converts a [String] to its corresponding [ExerciseCategory] value.
     *
     * @param name The name of the category.
     * @return The [ExerciseCategory] enum constant.
     */
    @TypeConverter
    fun toExerciseCategory(name: String): ExerciseCategory {
        return ExerciseCategory.valueOf(name)
    }
}