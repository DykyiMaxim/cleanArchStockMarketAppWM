package com.example.cleanarchstockmarketappwm.domain.repository

import com.example.cleanarchstockmarketappwm.domain.model.CompanyInfo
import com.example.cleanarchstockmarketappwm.domain.model.CompanyListing
import com.example.cleanarchstockmarketappwm.domain.model.IntradayInfo
import com.example.cleanarchstockmarketappwm.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getCompanyListings(
        fetchFromRemote:Boolean,
        query:String

    ): Flow<Resource<List<CompanyListing>>>

    suspend fun getIntradayInfo(
        symbol:String
    ):Resource<List<IntradayInfo>>

    suspend fun getcompanyInfo(
        symbol: String
    ):Resource<CompanyInfo>

}