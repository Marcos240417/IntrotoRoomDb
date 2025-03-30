package com.example.ui_compose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.introductiontoroom.introduction.data.model.PersonEntity
import com.example.ui_compose.dataaddres.AddressRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddressViewModel(
    private val repository: AddressRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    // Lista de endereços
    private val _searchedPersons = MutableStateFlow<List<PersonEntity>>(emptyList())
    val searchedPersons: StateFlow<List<PersonEntity>> get() = _searchedPersons

    // Estado de carregamento
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    // Estado de erro
    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> get() = _isError

    init {
        getAllAddresses()
    }

    // Obtém todos os endereços do Room
    private fun getAllAddresses() {
        viewModelScope.launch(dispatcher) {
            _isLoading.value = true
            _isError.value = false
            try {
                repository.getAllAddresses().collect { addresses ->
                    _searchedPersons.value = addresses // Atualiza a lista de endereços
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _isError.value = true
                _isLoading.value = false
            }
        }
    }

    // Insere ou atualiza um endereço no banco
    fun saveAddress(personEntity: PersonEntity) {
        viewModelScope.launch(dispatcher) {
            try {
                repository.insertAddress(personEntity)
                getAllAddresses() // Atualiza a lista após inserção ou atualização
            } catch (e: Exception) {
                _isError.value = true
            }
        }
    }

    // Deleta um endereço pelo ID
    fun deleteAddress(id: Int) {
        viewModelScope.launch(dispatcher) {
            try {
                repository.deleteAddressById(id)
                getAllAddresses() // Atualiza a lista após a exclusão
            } catch (e: Exception) {
                _isError.value = true
            }
        }
    }

    // Busca um endereço da API pelo CEP
    fun fetchAddressFromApi(cep: String) {
        viewModelScope.launch(dispatcher) {
            _isLoading.value = true
            _isError.value = false
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
                    _searchedPersons.value = listOf(apiAddress) // Atualiza a lista com o endereço da API
                    _isLoading.value = false
                } ?: run {
                    _isError.value = true
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _isError.value = true
                _isLoading.value = false
            }
        }
    }
}
