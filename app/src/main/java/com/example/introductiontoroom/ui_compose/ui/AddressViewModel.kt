package com.example.introductiontoroom.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.introductiontoroom.ui_compose.repositorys.AddressRepository
import com.example.introductiontoroom.ui_compose.repositorys.state.AddressFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddressViewModel(
    private val addressRepository: AddressRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddressFormUiState())
    val uiState = _uiState.asStateFlow()


    suspend fun findAddress(cep: String) {
        _uiState.update {
            it.copy(
                isLoading = true,
                isError = false
            )
        }
        _uiState.update {
            try {
                addressRepository.findAddress(cep)
                    .toAddressFormUiState()
            } catch (t: Throwable) {
                Log.e("AddressViewModel", "findAddress: ", t)
                _uiState.value.copy(
                    isError = true,
                    isLoading = false
                )
            }
        }
    }

}

