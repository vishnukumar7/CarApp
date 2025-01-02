package com.app.carsapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.carsapp.R
import com.app.carsapp.databinding.ActivityCarDetailBinding
import com.app.carsapp.model.CarResponse
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
    var edit : Boolean = false
    lateinit var carResponse: CarResponse

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
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.edit -> {
                edit = true
                binding.title.text=getString(R.string.car_edit_mode)
                binding.edit.visibility= View.GONE
                binding.submit.visibility=View.VISIBLE
                binding.carMake.isEnabled=true
                binding.carYear.isEnabled=true
                binding.carModel.isEnabled=true
                binding.carColor.isEnabled=true
                binding.carTransmission.isEnabled=true
                binding.carEngine.isEnabled=true
                binding.carHorsePower.isEnabled=true
            }
            R.id.back->{
                finish()
            }
            R.id.submit -> {
                edit =false
                binding.title.text=getString(R.string.car_details)
                binding.edit.visibility= View.VISIBLE
                binding.submit.visibility=View.GONE
                carResponse.make=binding.carMake.text.toString()
                carResponse.model=binding.carModel.text.toString()
                carResponse.year=binding.carYear.text.toString().toInt()
                carResponse.color=binding.carColor.text.toString()
                carResponse.transmission=binding.carTransmission.text.toString()
                carResponse.engine=binding.carEngine.text.toString()
                carResponse.horsepower=binding.carHorsePower.text.toString().toInt()
                binding.carMake.isEnabled=false
                binding.carYear.isEnabled=false
                binding.carModel.isEnabled=false
                binding.carColor.isEnabled=false
                binding.carTransmission.isEnabled=false
                binding.carEngine.isEnabled=false
                binding.carHorsePower.isEnabled=false
                viewModel.setData(carResponse)
            }
        }
    }


}