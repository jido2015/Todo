package com.a.todo.data

import androidx.room.TypeConverter

class Converter {

    //Convert Priority Object to string
    @TypeConverter
    fun fromPriority(priority: Priority): String{
        return priority.name
    }


    //Convert String to Priority Object
    @TypeConverter
    fun toPriority(priority: String): Priority{
        return Priority.valueOf(priority)
    }

}