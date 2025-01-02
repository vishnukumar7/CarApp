package com.app.carsapp.util

object Constants {
    fun ArrayList<String?>?.toStrings() : String{
        if(this.isNullOrEmpty())
            return ""
        var str=""
        this.forEach {
            str += "$it, "
        }
        return str.trim().substring(0,str.length-2)
    }
}