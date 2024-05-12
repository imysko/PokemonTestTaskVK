package com.imysko.pokemontesttaskvk.data.local

import androidx.room.TypeConverter

class DatabaseConvertors {

    @TypeConverter
    fun fromList(value: List<String>) = value.joinToString(separator = ",")

    @TypeConverter
    fun toList(value: String) = value.split(",")
}
