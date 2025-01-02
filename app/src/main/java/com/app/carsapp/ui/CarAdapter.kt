package com.app.carsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.carsapp.databinding.ListCarItemBinding
import com.app.carsapp.model.CarResponse
import com.bumptech.glide.Glide

class CarAdapter(val onclickListener: OnclickListener) : ListAdapter<CarResponse,CarAdapter.CarViewHolder>(
    DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
       val binding = ListCarItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CarViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(getItem(position),onclickListener)

    }

    class CarViewHolder(private val binding: ListCarItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(carResponse: CarResponse,onclickListener: OnclickListener){
            Glide.with(binding.root)
                .load(carResponse.image)
                .into(binding.carImage)
            binding.carMake.text= carResponse.make
            binding.carModel.text=carResponse.model
            binding.carLay.setOnClickListener {
                onclickListener.onClick(carResponse.id)
            }
        }
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CarResponse>() {
            override fun areItemsTheSame(oldItem: CarResponse, newItem: CarResponse): Boolean {
                    return oldItem.id==newItem.id
            }

            override fun areContentsTheSame(oldItem: CarResponse, newItem: CarResponse): Boolean {
                return oldItem == newItem
            }

        }
    }

    interface OnclickListener{
        fun onClick(carId: Int)
    }
}