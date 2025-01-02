package com.app.carsapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.carsapp.R
import com.app.carsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CarAdapter.OnclickListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var adapter : CarAdapter
    private val viewModel : MainViewModel by viewModels()
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = CarAdapter(this)
        binding.recyclerView.adapter=adapter
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        viewModel.items.observe(this){
            Log.e("TAG", "onCreate: $it", )
            if(it.isEmpty())
                viewModel.refreshItems()
            else{
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            }
        }
       // viewModel.refreshItems()
    }

    override fun onClick(carId: Int) {
        val intent = Intent(this,CarDetailActivity::class.java)
        intent.putExtra(CarDetailActivity.CAR_ID,carId)
        startActivity(intent)
    }
}