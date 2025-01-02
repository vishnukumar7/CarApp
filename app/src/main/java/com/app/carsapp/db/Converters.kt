package com.app.carsapp.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters{
    @TypeConverter
    fun fromArrayList(arrayList: ArrayList<String?>?): String? {
        if(arrayList.isNullOrEmpty())
            return null
        return Gson().toJson(arrayList)
    }

    @TypeConverter
    fun toArrayList(valueString : String?) : ArrayList<String>{
        if(valueString.isNullOrBlank())
            return  ArrayList()
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(valueString,listType)
    }
}