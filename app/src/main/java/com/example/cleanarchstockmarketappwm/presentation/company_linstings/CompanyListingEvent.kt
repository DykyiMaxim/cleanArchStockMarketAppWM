package com.example.cleanarchstockmarketappwm.presentation.company_linstings

sealed class CompanyListingEvent{
    object Refresh :CompanyListingEvent()
    data class OnSearchQueryChange(val query:String):CompanyListingEvent()
}
