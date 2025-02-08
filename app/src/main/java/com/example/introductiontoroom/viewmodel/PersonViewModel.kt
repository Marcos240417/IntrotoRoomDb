package com.example.introductiontoroom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.introductiontoroom.data.PersonRepositoryImpl
import com.example.introductiontoroom.data.model.PersonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PersonViewModel(private val personRepositoryImpl: PersonRepositoryImpl) : ViewModel() {

    // Convertendo Flow para LiveData
    val allPersons: LiveData<List<PersonEntity>> = personRepositoryImpl.getAllPerson().asLiveData()
    private val _searchedPersons = MutableLiveData<List<PersonEntity>>()
    val searchedPersons: LiveData<List<PersonEntity>> get() = _searchedPersons

    // Função para adicionar uma pessoa
    fun addPerson(personEntity: PersonEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            personRepositoryImpl.insertPerson(personEntity)
        }
    }

    // Função para excluir uma pessoa
    fun deletePerson(personEntity: PersonEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            personRepositoryImpl.deletePerson(personEntity)
        }
    }

    // Função para atualizar os dados de uma pessoa
    fun updatePerson(personEntity: PersonEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            personRepositoryImpl.updatePerson(personEntity)
        }
    }

    // Função para excluir uma pessoa pelo ID
    fun deletePersonById(personEntity: PersonEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            personRepositoryImpl.deletePersonById(personEntity)
        }
    }

    // Função para procurar uma pessoa com base no nome, idade ou cidade
    fun getSearchedData(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result: Flow<List<PersonEntity>> = personRepositoryImpl.getSearchedData(query)
            result.collect { personList ->
                _searchedPersons.postValue(personList)
            }
        }
    }
}
