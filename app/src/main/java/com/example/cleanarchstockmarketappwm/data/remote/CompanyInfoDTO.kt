package com.example.cleanarchstockmarketappwm.data.remote

import com.squareup.moshi.Json

data class CompanyInfoDTO(
    @field:Json(name = "Symbol") val Symbol:String,
    @field:Json(name = "Description") val description:String,
    @field:Json(name = "Name") val name:String,
    @field:Json(name = "Country") val Country:String,
    @field:Json(name = "Industry") val Industry:String,
)
