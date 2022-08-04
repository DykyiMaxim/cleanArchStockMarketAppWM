package com.example.cleanarchstockmarketappwm.data.mapper

import com.example.cleanarchstockmarketappwm.data.remote.IntaradayInfoDto
import com.example.cleanarchstockmarketappwm.domain.model.IntradayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun IntaradayInfoDto.toIntaradayInfo():IntradayInfo{
    val pattern = "yyyy-MM-dd HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(timestamp, formatter)
    return IntradayInfo(
        date = localDateTime,
        close = close
    )
}