package com.example.ui_compose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.introductiontoroom.introduction.data.model.PersonEntity
import com.example.introductiontoroom.introduction.data.PersonRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddressViewModel(
    private val repository: PersonRepository, // Agora usa o PersonRepository unificado
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    // Lista de endereços armazenados no banco de dados
    private val _searchedPersons = MutableStateFlow<List<PersonEntity>>(emptyList())
    val searchedPersons: StateFlow<List<PersonEntity>> get() = _searchedPersons

    init {
        getAllAddresses()
    }

    /** Obtém todos os endereços armazenados localmente */
    private fun getAllAddresses() {
        viewModelScope.launch(dispatcher) {
            repository.getAllPerson().collect { addresses ->
                _searchedPersons.value = addresses
            }
        }
    }

    /** Insere ou atualiza um endereço no banco */
    fun saveAddress(personEntity: PersonEntity) {
        viewModelScope.launch(dispatcher) {
            repository.updatePerson(personEntity)
            getAllAddresses() // Atualiza lista após operação
        }
    }

    /** Busca um endereço na API pelo CEP */
    fun fetchAddressFromApi(cep: String, personEntity: PersonEntity) {
        viewModelScope.launch(dispatcher) {
            val response = repository.fetchAddressFromApi(cep)
            response?.let {
                val personWithCep = personEntity.copy(
                    cep = it.cep,
                    logradouro = it.logradouro,
                    bairro = it.bairro,
                    cidade = it.localidade,
                    estado = it.estado,
                )
                _searchedPersons.value = listOf(personWithCep)
            }
        }
    }
}
