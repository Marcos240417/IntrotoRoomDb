package com.example.ui_compose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.introductiontoroom.introduction.data.model.PersonEntity
import com.example.ui_compose.dataaddres.AddressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Modelo para o estado único
data class AddressUiState(
    val selectedAddress: PersonEntity? = null, // Endereço selecionado (atual)
    val addressList: List<PersonEntity> = emptyList(), // Lista de endereços
    val isLoading: Boolean = false, // Indicador de carregamento
    val isError: Boolean = false // Indicador de erro
)

class AddressViewModel(
    private val repository: AddressRepository // Repositório para manipular Room e API
) : ViewModel() {

    // Estado único para gerenciar tudo
    private val _uiState = MutableStateFlow(AddressUiState())
    val uiState: StateFlow<AddressUiState> get() = _uiState

    // Busca todos os endereços do Room
    fun getAllAddresses() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, isError = false)
            try {
                repository.getAllAddresses().collect { addresses ->
                    _uiState.value = _uiState.value.copy(
                        addressList = addresses,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    addressList = emptyList(),
                    isLoading = false,
                    isError = true
                )
            }
        }
    }

    // Busca um endereço do Room com base na chave primária (pId)
    fun getAddressById(id: Int) {
        viewModelScope.launch {
            try {
                val addressList = _uiState.value.addressList
                val person = addressList.firstOrNull { it.pId == id }
                _uiState.value = _uiState.value.copy(selectedAddress = person)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(selectedAddress = null)
            }
        }
    }

    // Insere ou atualiza um endereço no Room
    fun saveAddress(personEntity: PersonEntity) {
        viewModelScope.launch {
            try {
                repository.insertAddress(personEntity)
                _uiState.value = _uiState.value.copy(selectedAddress = personEntity)
                getAllAddresses() // Atualiza a lista
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isError = true)
            }
        }
    }

    // Deleta um endereço do Room com base na chave primária
    fun deleteAddress(id: Int) {
        viewModelScope.launch {
            try {
                repository.deleteAddressById(id)
                _uiState.value = _uiState.value.copy(selectedAddress = null)
                getAllAddresses() // Atualiza a lista de endereços
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isError = true)
            }
        }
    }

    // Busca um único endereço pela API e exibe antes de salvar no banco
    fun fetchAddressFromApi(cep: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, isError = false)
            try {
                val response = repository.fetchAddressFromApi(cep)
                response?.let {
                    val apiAddress = PersonEntity(
                        pId = 0,
                        name = "",
                        dateBirth = "",
                        nsus = "",
                        cep = it.cep,
                        logradouro = it.logradouro,
                        number = "",
                        bairro = it.bairro,
                        cidade = it.localidade,
                        estado = it.estado,
                        sexo = "",
                        maritalStatus = "",
                        nationality = "",
                        identityRG = "",
                        identityCPF = "",
                        phone = "",
                        email = null
                    )
                    _uiState.value = _uiState.value.copy(
                        selectedAddress = apiAddress,
                        isLoading = false
                    )
                } ?: run {
                    _uiState.value = _uiState.value.copy(isLoading = false, isError = true)
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, isError = true)
            }
        }
    }

    // Confirma o salvamento do endereço atualmente selecionado
    fun confirmSaveAddress() {
        _uiState.value.selectedAddress?.let {
            saveAddress(it)
        }
    }
}
