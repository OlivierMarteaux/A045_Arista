package com.openclassrooms.arista.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class representing a user profile.
 *
 * Stored in the "user" table of the Room database.
 *
 * @property id Auto-generated unique identifier for the user.
 * @property name The user's name.
 * @property email The user's email address.
 */
@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var name: String,
    var email: String
)