package com.example.cleanarchstockmarketappwm.presentation.company_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchstockmarketappwm.domain.repository.StockRepository
import com.example.cleanarchstockmarketappwm.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyInfoViewModel@Inject constructor(
    private val savedStateHandler:SavedStateHandle,
    private val repository: StockRepository
):ViewModel() {
    var state by mutableStateOf(CompanyInfoState())

    init{
        viewModelScope.launch {
            val symbol = savedStateHandler.get<String>("symbol")?:return@launch
            state = state.copy(isLoading = true)
            val companyInfoResult = async {   repository.getcompanyInfo(symbol)}
            val itradayInfoResult = async {   repository.getIntradayInfo(symbol)}
            when(val result = companyInfoResult.await()){
                is Resource.Success ->{
                    state = state.copy(
                        company = result.data,
                        isLoading = false,
                        error = null
                    )

                }
                is Resource.Error->{
                    state = state.copy(
                        isLoading = false,
                        error = result.message,
                        company = null
                    )

                }
                else ->Unit
            }

            when(val result = itradayInfoResult.await()){
                is Resource.Success ->{
                    state = state.copy(
                        stockInfos = result.data?: emptyList(),
                        isLoading = false,
                        error = null
                    )

                }
                is Resource.Error->{
                    state = state.copy(
                        isLoading = false,
                        error = result.message,
                        company = null
                    )

                }
                else ->Unit
            }

        }
    }


}