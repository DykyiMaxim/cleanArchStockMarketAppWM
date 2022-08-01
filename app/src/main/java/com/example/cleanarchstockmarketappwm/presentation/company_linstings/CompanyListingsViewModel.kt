package com.example.cleanarchstockmarketappwm.presentation.company_linstings

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchstockmarketappwm.domain.repository.StockRepository
import com.example.cleanarchstockmarketappwm.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyListingsViewModel@Inject constructor(
    private val repository:StockRepository
):ViewModel() {
    var state by mutableStateOf(CompanyListingsState())
    private var searchJob: Job? = null

    fun OnEvent(event:CompanyListingEvent){
        when(event){
            is CompanyListingEvent.Refresh->{
                getCompanyListings(
                    fetchFromRemote = true
                )


            }
            is CompanyListingEvent.OnSearchQueryChange->{
                state = state.copy(
                    searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)   //for don't spamming query's
                    getCompanyListings()
                }

            }
        }
    }

   private fun getCompanyListings(
        query:String=state.searchQuery.lowercase(),
        fetchFromRemote:Boolean = false
    ){
        viewModelScope.launch {
            repository
                .getCompanyListings(fetchFromRemote,query)
                .collect{result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let{listings->
                                state = state.copy(
                                    companies = listings
                                )

                            }

                        }
                        is Resource.Error->{
                            Log.d("Err",result.message.toString())

                        }
                        is Resource.Loading->{
                            state =state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }
}