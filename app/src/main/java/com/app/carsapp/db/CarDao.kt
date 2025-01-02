package com.app.carsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.app.carsapp.model.CarResponse

@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(carResponse: CarResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(carResponse: List<CarResponse>)

    @Query("select * from cars")
    fun getAllCars() : LiveData<List<CarResponse>>

    @Query("select * from cars where id=:id")
    fun getCarByID(id : Int) : LiveData<CarResponse>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCar(carResponse: CarResponse)
}