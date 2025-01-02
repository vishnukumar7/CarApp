package com.app.carsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.carsapp.model.CarResponse

@Database(entities = [CarResponse::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabases  : RoomDatabase(){
    abstract fun carsDao() : CarDao
}