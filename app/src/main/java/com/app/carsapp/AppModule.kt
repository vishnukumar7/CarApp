package com.app.carsapp

import android.content.Context
import androidx.room.Room
import com.app.carsapp.api.ApiInterface
import com.app.carsapp.db.AppDatabases
import com.app.carsapp.db.CarDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideApiInterface() : ApiInterface{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface::class.java)
    }

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabases{
        return Room.databaseBuilder(context,AppDatabases::class.java,"car_database")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCarDao(databases: AppDatabases) : CarDao = databases.carsDao()
}