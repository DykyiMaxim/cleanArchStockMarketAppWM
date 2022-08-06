package com.example.cleanarchstockmarketappwm.presentation.company_info

import com.example.cleanarchstockmarketappwm.domain.model.CompanyInfo
import com.example.cleanarchstockmarketappwm.domain.model.IntradayInfo

data class CompanyInfoState(
    val stockInfos:List<IntradayInfo> = emptyList(),
    val company:CompanyInfo?=null,
    val isLoading:Boolean = false,
    val error:String? = null

)
