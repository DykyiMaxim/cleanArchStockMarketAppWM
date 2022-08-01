package com.example.cleanarchstockmarketappwm.data.repository

import com.example.cleanarchstockmarketappwm.data.csv.CSVParser
import com.example.cleanarchstockmarketappwm.data.csv.CompanyListingsParser
import com.example.cleanarchstockmarketappwm.data.local.StockDataBase
import com.example.cleanarchstockmarketappwm.data.mapper.toCompanyEntity
import com.example.cleanarchstockmarketappwm.data.mapper.toCompanyListing
import com.example.cleanarchstockmarketappwm.data.remote.StockApi
import com.example.cleanarchstockmarketappwm.domain.model.CompanyListing
import com.example.cleanarchstockmarketappwm.domain.repository.StockRepository
import com.example.cleanarchstockmarketappwm.util.Resource
import com.opencsv.CSVReader
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    val api:StockApi,
    val db:StockDataBase,
    val companyListingsParser: CSVParser<CompanyListing>
):StockRepository {
    private val dao = db.dao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow{
            emit(Resource.Loading(true)) //drop data to flow catch in VM
            val locaListings = dao.searchCompanyListing(query)
            emit(Resource.Success(
                data = locaListings.map{it.toCompanyListing()
                }
            ))
            val isDEmpty = locaListings.isEmpty()&& query.isBlank()

            val shouldLoadFromCash = !isDEmpty&& !fetchFromRemote

            if(shouldLoadFromCash){
                emit(Resource.Loading(false))
                return@flow
            }
            val remoListings = try{
                val response = api.getListings()

                companyListingsParser.parse(response.byteStream())

            }catch (e: HttpException){
                e.printStackTrace()
                emit(Resource.Error("Cant Load Data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }


            remoListings?.let{listings ->
                emit(Resource.Success(listings))
                emit(Resource.Loading(false))
                dao.clearCompanyListings()
                dao.insertCompanyListings(
                    listings.map{it.toCompanyEntity()}
                )
                emit(Resource.Success(
                    data = dao.searchCompanyListing("").map{it.toCompanyListing()}
                )
                )
                //emit(Resource.Loading(false))

            }


        }
    }
}