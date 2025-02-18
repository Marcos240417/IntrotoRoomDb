package com.example.introductiontoroom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.introductiontoroom.data.PersonRepository
import com.example.introductiontoroom.data.model.PersonEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PersonViewModel(
    private val personRepository: PersonRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    /*
    Na ViewModel, você está utilizando o LiveData de duas maneiras diferentes para exibir listas
    do mesmo tipo.

    Atividade:

    Reduza a quantidade de código e utilize o LiveData apenas uma vez, atendendo à funcionalidade
    de busca com e sem query de pesquisa.
     */

    // Convertendo Flow para LiveData
    //val allPersons: LiveData<List<PersonEntity>> = personRepository.getAllPerson().asLiveData()
    private val _searchedPersons = MutableLiveData<List<PersonEntity>>()
    val searchedPersons: LiveData<List<PersonEntity>> get() = _searchedPersons

    init {
        // Inicializa com a lista completa de pessoas
        getAllPersons()
    }

    // Função para obter todas as pessoas
    private fun getAllPersons() {
        viewModelScope.launch(dispatcher) {
            personRepository.getAllPerson().collect { personList ->
                _searchedPersons.postValue(personList)
            }
        }
    }

    // Função para adicionar uma pessoa
    fun addPerson(personEntity: PersonEntity) {
        viewModelScope.launch(dispatcher) {
            personRepository.insertPerson(personEntity)
        }
    }

    // Função para excluir uma pessoa
    fun deletePerson(personEntity: PersonEntity) {
        viewModelScope.launch(dispatcher) {
            personRepository.deletePerson(personEntity)
        }
    }

    // Função para atualizar os dados de uma pessoa
    fun updatePerson(personEntity: PersonEntity) {
        viewModelScope.launch(dispatcher) {
            personRepository.updatePerson(personEntity)
        }
    }

    // Função para excluir uma pessoa pelo ID
    fun deletePersonById(personEntity: PersonEntity) {
        viewModelScope.launch(dispatcher) {
            personRepository.deletePersonById(personEntity)
        }
    }

    // Função para procurar uma pessoa com base no nome, idade ou cidade
    fun getSearchedData(query: String = "") {
        viewModelScope.launch(dispatcher) {
            val result: Flow<List<PersonEntity>> = personRepository.getSearchedData(query)
            result.collect { personList ->
                _searchedPersons.postValue(personList)
            }
        }
    }
}
