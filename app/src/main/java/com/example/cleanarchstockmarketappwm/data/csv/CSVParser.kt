package com.example.cleanarchstockmarketappwm.data.csv

import java.io.InputStream

interface CSVParser<T> {
    suspend fun parse(stream:InputStream):List<T>
}