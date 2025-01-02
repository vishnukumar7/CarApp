package com.app.carsapp.util

import android.view.View

object Constants {
    fun ArrayList<String?>?.toStrings() : String{
        if(this.isNullOrEmpty())
            return ""
        var str=""
        this.forEachIndexed { index, it ->
            if(index==this.size-1){
                str += "$it"
            }else{
                str += "$it\n"
            }
        }
        return str.trim().substring(0,str.length-2)
    }

    fun View.hide(){
        this.visibility=View.GONE
    }

    fun View.show(){
        this.visibility=View.VISIBLE
    }


}