package database

import androidx.room.TypeConverter
import java.util.Date

class CrimeTypeConverter {
    // type converters basically translate between class attributes and database attributes
    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long): Date {
        return Date(millisSinceEpoch)
    }
}
