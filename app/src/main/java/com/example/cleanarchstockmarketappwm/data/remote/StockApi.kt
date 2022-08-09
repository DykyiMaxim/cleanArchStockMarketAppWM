package com.example.cleanarchstockmarketappwm.data.remote

import com.example.cleanarchstockmarketappwm.BuildConfig
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apiKey:String = API_KEY
    ):ResponseBody

    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv")
    suspend fun getIndastryInfo(
        @Query("symbol") symbol:String,
        @Query("apikey") apiKey: String = API_KEY
    ):ResponseBody

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(
        @Query("symbol") symbol:String,
        @Query("apikey") apiKey: String = API_KEY
    ):CompanyInfoDTO


    companion object{
        const val API_KEY=BuildConfig.API_KEY
        const val Base_URL = "https://alphavantage.co"
    }
}