package com.example.cleanarchstockmarketappwm.presentation.company_linstings

import com.example.cleanarchstockmarketappwm.domain.model.CompanyListing

data class CompanyListingsState(
    val companies:List<CompanyListing> = emptyList(),
    val isLoading:Boolean = false,
    val IsRefreshing:Boolean = false,
    val searchQuery:String = ""

)
