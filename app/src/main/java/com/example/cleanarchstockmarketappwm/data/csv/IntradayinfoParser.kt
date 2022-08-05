package com.example.cleanarchstockmarketappwm.data.csv

import com.example.cleanarchstockmarketappwm.data.mapper.toIntaradayInfo
import com.example.cleanarchstockmarketappwm.data.remote.IntaradayInfoDto
import com.example.cleanarchstockmarketappwm.domain.model.CompanyListing
import com.example.cleanarchstockmarketappwm.domain.model.IntradayInfo
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton

class IntradayinfoParser @Inject constructor():CSVParser<IntradayInfo> {
    override suspend fun  parse(stream: InputStream): List<IntradayInfo> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO){
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line->
                    val timestamp = line.getOrNull(0)?:return@mapNotNull null
                    val close = line.getOrNull(4)?:return@mapNotNull null
                    val dto = IntaradayInfoDto(timestamp,close.toDouble())
                    dto.toIntaradayInfo()
                }
                .filter {
                    it.date.dayOfMonth ==LocalDate.now().minusDays(1).dayOfMonth
                }
                .sortedBy { it.date.hour }
                .also { csvReader.close() }
        }
    }
}