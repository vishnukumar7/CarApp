package com.app.carsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.carsapp.model.CarResponse
import com.app.carsapp.util.CarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val carRepository: CarRepository) : ViewModel(){

    val items : LiveData<List<CarResponse>> = carRepository.cars

    fun refreshItems(){
        viewModelScope.launch {
            carRepository.refreshItems()
        }
    }

    fun getCarId(id : Int) : LiveData<CarResponse>{
        return carRepository.getCarId(id)
    }

    fun setData(carResponse: CarResponse)=
        viewModelScope.launch {
            carRepository.update(carResponse)
        }
}
