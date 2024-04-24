package com.example.pestotodoapplication.data

import androidx.room.TypeConverter
import com.example.pestotodoapplication.data.models.Priority

/**
 * author lgarg on 4/23/24.
 */
class Converter {

    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}