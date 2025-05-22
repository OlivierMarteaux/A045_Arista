package com.openclassrooms.arista.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "sleep")
data class Sleep(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @JvmField var startTime: LocalDateTime,
    var duration: Int,
    var quality: Int
)
