package com.openclassrooms.arista.domain.model

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Date

class DateConverter {
    @TypeConverter
    fun toLocalDateTime(timestamp: Long?): LocalDateTime? {
        return timestamp?.let {
            LocalDateTime.ofEpochSecond(it, 0, ZoneOffset.UTC)
        }
    }
    @TypeConverter
    fun fromlocalDateTime(localDateTime: LocalDateTime?): Long? {
        return localDateTime?.toEpochSecond(ZoneOffset.UTC)
    }
}