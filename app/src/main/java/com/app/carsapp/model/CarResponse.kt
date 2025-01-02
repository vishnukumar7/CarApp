package com.app.carsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cars")
data class CarResponse(

    @ColumnInfo
    @field:SerializedName("image")
    var image: String="",

    @ColumnInfo
    @field:SerializedName("color")
    var color: String = "",

    @ColumnInfo
    @field:SerializedName("horsepower")
    var horsepower: Int =-1,

    @ColumnInfo
    @field:SerializedName("year")
    var year: Int = -1,

    @ColumnInfo
    @field:SerializedName("owners")
    var owners: Int = -1,

    @ColumnInfo
    @field:SerializedName("features")
    var features: ArrayList<String?>? = ArrayList(),

    @ColumnInfo
    @field:SerializedName("transmission")
    var transmission: String="",

    @ColumnInfo
    @field:SerializedName("fuelType")
    var fuelType: String ="",

    @ColumnInfo
    @field:SerializedName("engine")
    var engine: String ="",

    @ColumnInfo
    @field:SerializedName("price")
    var price: Int =-1,

    @ColumnInfo
    @field:SerializedName("model")
    var model: String  = "",

    @PrimaryKey
    @field:SerializedName("id")
    var id: Int =  -1,

    @ColumnInfo
    @field:SerializedName("make")
    var make: String = "",

    @ColumnInfo
    @field:SerializedName("mileage")
    var mileage: Int = -1
)
