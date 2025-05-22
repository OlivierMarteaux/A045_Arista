package com.openclassrooms.arista.domain.model

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Date

class DateConverter {
    @TypeConverter
    fun toLocalDateTime(timestamp: Long?): LocalDateTime? {
        return timestamp?.let {
//            val seconds = it / 100
//            val hundredths = (it % 100).toInt()
//            val nanos = hundredths * 10_000_000  // 1/100s = 10 million nanoseconds
//            LocalDateTime.ofEpochSecond(seconds, nanos, ZoneOffset.UTC)
            LocalDateTime.ofEpochSecond(it, 0, ZoneOffset.UTC)
        }
    }
    @TypeConverter
    fun fromlocalDateTime(localDateTime: LocalDateTime?): Long? {
        return localDateTime?.toEpochSecond(ZoneOffset.UTC)
    }
}