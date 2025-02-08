package com.example.introductiontoroom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.introductiontoroom.data.PersonRepository
import com.example.introductiontoroom.data.model.PersonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PersonViewModel(private val personRepository: PersonRepository) : ViewModel() {

    // Convertendo Flow para LiveData
    val allPersons: LiveData<List<PersonEntity>> = personRepository.getAllPerson().asLiveData()
    private val _searchedPersons = MutableLiveData<List<PersonEntity>>()
    val searchedPersons: LiveData<List<PersonEntity>> get() = _searchedPersons

    // Função para adicionar uma pessoa
    fun addPerson(personEntity: PersonEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            personRepository.insertPerson(personEntity)
        }
    }

    // Função para excluir uma pessoa
    fun deletePerson(personEntity: PersonEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            personRepository.deletePerson(personEntity)
        }
    }

    // Função para atualizar os dados de uma pessoa
    fun updatePerson(personEntity: PersonEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            personRepository.updatePerson(personEntity)
        }
    }

    // Função para excluir uma pessoa pelo ID
    fun deletePersonById(personEntity: PersonEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            personRepository.deletePersonById(personEntity)
        }
    }

    // Função para procurar uma pessoa com base no nome, idade ou cidade
    fun getSearchedData(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result: Flow<List<PersonEntity>> = personRepository.getSearchedData(query)
            result.collect { personList ->
                _searchedPersons.postValue(personList)
            }
        }
    }
}
