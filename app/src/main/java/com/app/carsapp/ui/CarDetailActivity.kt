package com.app.carsapp.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.carsapp.R
import com.app.carsapp.databinding.ActivityCarDetailBinding
import com.app.carsapp.model.CarResponse
import com.app.carsapp.util.Constants.hide
import com.app.carsapp.util.Constants.show
import com.app.carsapp.util.Constants.toStrings
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarDetailActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        const val CAR_ID="car_id"
    }
    private lateinit var binding: ActivityCarDetailBinding
    private val viewModel: MainViewModel by viewModels()
    private var edit : Boolean = false
    private lateinit var carResponse: CarResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener(this)
        intent?.getIntExtra(CAR_ID,-1)?.let {
            viewModel.getCarId(it).observe(this@CarDetailActivity){car->
                    Glide.with(this@CarDetailActivity)
                        .load(car.image)
                        .into(binding.carImage)
                carResponse=car
                binding.data=car
                binding.features.setText(car.features.toStrings())
            }
        }

        binding.edit.setOnClickListener(this)
        binding.submit.setOnClickListener(this)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(enabled = true) {
            override fun handleOnBackPressed() {
                back()
            }

        })
    }

    fun back(){
        if(edit){
            val alertBuild = AlertDialog.Builder(this)
            alertBuild.setMessage("Discard Changes?")
            alertBuild.setTitle("Alert!!")
            alertBuild.setCancelable(false)
            alertBuild.setPositiveButton("Yes"){
                dialog,which->finish()
            }
            alertBuild.setNegativeButton("No"){
                dialog,which -> dialog.cancel()
            }
            alertBuild.setNeutralButton("Save and exit"){ dialog,which ->
                run {
                    saveData()
                    finish()
                }
            }
            alertBuild.show()
        }else finish()
    }

    fun saveData(){
        carResponse.make=binding.carMake.text.toString().trim()
        carResponse.model=binding.carModel.text.toString().trim()
        carResponse.year=binding.carYear.text.toString().trim().toInt()
        carResponse.color=binding.carColor.text.toString().trim()
        carResponse.transmission=binding.carTransmission.text.trim().toString()
        carResponse.engine=binding.carEngine.text.toString().trim()
        carResponse.horsepower=binding.carHorsePower.text.toString().trim().toInt()
        viewModel.setData(carResponse)
    }

    fun enabled(value : Boolean){
        binding.carMake.isEnabled=value
        binding.carYear.isEnabled=value
        binding.carModel.isEnabled=value
        binding.carColor.isEnabled=value
        binding.carTransmission.isEnabled=value
        binding.carEngine.isEnabled=value
        binding.carHorsePower.isEnabled=value
    }


    override fun onClick(view: View?) {
        when(view?.id){
            R.id.edit -> {
                edit = true
                binding.title.text=getString(R.string.car_edit_mode)
                binding.edit.hide()
                binding.submit.show()
                enabled(true)
            }
            R.id.back->{
                back()
            }
            R.id.submit -> {
                edit =false
                binding.title.text=getString(R.string.car_details)
                binding.edit.show()
                binding.submit.hide()
                saveData()
                enabled(false)

            }
        }
    }

}