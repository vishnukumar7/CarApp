package com.app.carsapp.util

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.carsapp.api.ApiInterface
import com.app.carsapp.db.CarDao
import com.app.carsapp.model.CarResponse
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CarRepository @Inject constructor(private val carDao: CarDao,private val apiInterface: ApiInterface) {

    val cars = carDao.getAllCars()

    suspend fun refreshItems(){
        val response = apiInterface.getCarsList()
        if(response.isSuccessful && response.code()==200){
            Log.e("TAG", "refreshItems: ${Gson().toJson(response.body())}", )
            response.body()?.let {
                carDao.insertCar(it)
            }
        }
    }

    fun getCarId(id: Int) : LiveData<CarResponse>{
          return carDao.getCarByID(id)
    }
    suspend fun update(carResponse: CarResponse) = carDao.updateCar(carResponse)
}