package com.example.ui_compose.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ui_compose.dataaddres.AddressRepository
import com.example.ui_compose.dataaddres.model.AddressResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddressViewModel(
    private val repository: AddressRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddressResponse(data = arrayListOf()))
    val uiState = _uiState.asStateFlow()

    suspend fun findAddress(cep: String) {
        // Atualiza estado para "carregando"
        _uiState.update {
            it.copy(
                isLoading = true,
                isError = false
            )
        }
        _uiState.update {
            try {
                repository.findAddress(cep)
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