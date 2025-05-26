package com.openclassrooms.arista.domain.model

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Date

/**
 * Converter class for transforming [LocalDateTime] objects to and from [Long] timestamps.
 *
 * Used by Room to persist date-time data in a format that SQLite can store.
 */
class DateConverter {
    /**
     * Converts a [Long] timestamp to a [LocalDateTime] in UTC.
     *
     * @param timestamp The timestamp in seconds since the epoch.
     * @return A [LocalDateTime] instance, or `null` if [timestamp] is null.
     */
    @TypeConverter
    fun toLocalDateTime(timestamp: Long?): LocalDateTime? {
        return timestamp?.let {
            LocalDateTime.ofEpochSecond(it, 0, ZoneOffset.UTC)
        }
    }
    /**
     * Converts a [LocalDateTime] to a [Long] timestamp in seconds.
     *
     * @param localDateTime The date-time to convert.
     * @return The number of seconds since epoch, or `null` if [localDateTime] is null.
     */
    @TypeConverter
    fun fromlocalDateTime(localDateTime: LocalDateTime?): Long? {
        return localDateTime?.toEpochSecond(ZoneOffset.UTC)
    }
}