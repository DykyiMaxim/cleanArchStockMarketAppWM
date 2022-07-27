package com.example.cleanarchstockmarketappwm.data.mapper

import com.example.cleanarchstockmarketappwm.data.local.CompanyListingEntity
import com.example.cleanarchstockmarketappwm.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )

}

fun CompanyListing.toCompanyEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )

}