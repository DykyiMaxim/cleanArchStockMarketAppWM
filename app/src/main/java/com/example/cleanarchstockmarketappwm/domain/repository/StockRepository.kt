package com.example.cleanarchstockmarketappwm.domain.repository

import com.example.cleanarchstockmarketappwm.domain.model.CompanyListing
import com.example.cleanarchstockmarketappwm.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getCompanyListings(
        fetchFromRemote:Boolean,
        query:String

    ): Flow<Resource<List<CompanyListing>>>

}