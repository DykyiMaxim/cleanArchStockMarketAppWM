package com.example.cleanarchstockmarketappwm.di

import android.app.Application
import androidx.room.Room
import com.example.cleanarchstockmarketappwm.data.local.StockDataBase
import com.example.cleanarchstockmarketappwm.data.remote.StockApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideStockApi():StockApi{

        return Retrofit
            .Builder()
            .baseUrl(StockApi.Base_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }).build())
            .build()
            .create()

    }
    @Provides
    @Singleton
    fun provideStockDataBase(
        app:Application
    ):StockDataBase{
        return Room.databaseBuilder(
            app,
            StockDataBase::class.java,
            "stock.db"
        ).build()
    }
}