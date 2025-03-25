package com.example.ui_compose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.introductiontoroom.introduction.data.model.PersonEntity
import com.example.ui_compose.dataaddres.AddressRepository
import com.example.ui_compose.dataaddres.model.AddressMapper
import com.example.ui_compose.dataaddres.model.AddressResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddressViewModel(
    private val repository: AddressRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddressResponse())
    val uiState = _uiState.asStateFlow()

    fun findAddress(cep: String, personEntity: PersonEntity) {
        viewModelScope.launch {
            val response = repository.findAddress(cep)
            response.let {
                val updatedPerson = AddressMapper.toPersonEntity(it, personEntity)
                repository.updatePersonInRoom(updatedPerson)
            }
        }
    }

    /*suspend fun findAddress(cep: String) {
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
    }*/
}