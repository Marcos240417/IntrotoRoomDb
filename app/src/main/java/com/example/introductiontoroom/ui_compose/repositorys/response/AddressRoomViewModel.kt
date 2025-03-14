package com.example.introductiontoroom.ui_compose.repositorys.response

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.introductiontoroom.ui_compose.repositorys.state.AddressFormUiState
import kotlinx.coroutines.launch

class AddressRoomViewModel(private val repository: AddressRoomRepository) : ViewModel() {
    private val _uiState = MutableLiveData<AddressFormUiState>()
    val uiState: LiveData<AddressFormUiState> = _uiState

    fun buscarEndereco(cep: String) {
        viewModelScope.launch {
            _uiState.value = AddressFormUiState(isLoading = true)

            val result = repository.buscarEConverterEndereco(cep)

            if (result != null) {
                // Atualiza a UI com os dados obtidos
                _uiState.value = AddressFormUiState(
                    logradouro = result.rua,
                    bairro = result.bairro,
                    localidade = result.cidade,
                    estado = result.estado,
                    isLoading = false,
                    isError = false
                )
            } else {
                _uiState.value = AddressFormUiState(isLoading = false, isError = true)
            }
        }
    }
}
