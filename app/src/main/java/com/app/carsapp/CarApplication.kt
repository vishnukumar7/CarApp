package com.app.carsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CarApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }

}