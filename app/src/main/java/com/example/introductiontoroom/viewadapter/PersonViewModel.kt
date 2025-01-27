package com.example.introductiontoroom.viewadapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.introductiontoroom.data.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PersonViewModel(private val personRepository: PersonRepository) : ViewModel() {

    // Convertendo Flow para LiveData
    val allPersons: LiveData<List<Person>> = personRepository.getAllPerson().asLiveData()
    private val _searchedPersons = MutableLiveData<List<Person>>()
    val searchedPersons: LiveData<List<Person>> get() = _searchedPersons

    // Função para adicionar uma pessoa
    fun addPerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            personRepository.insertPerson(person)
        }
    }

    // Função para excluir uma pessoa
    fun deletePerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            personRepository.deletePerson(person)
        }
    }

    // Função para atualizar os dados de uma pessoa
    fun updatePerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            personRepository.updatePerson(person)
        }
    }

    // Função para excluir uma pessoa pelo ID
    fun deletePersonById(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            personRepository.deletePersonById(person)
        }
    }

    // Função para procurar uma pessoa com base no nome, idade ou cidade
    fun getSearchedData(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result: Flow<List<Person>> = personRepository.getSearchedData(query)
            result.collect { personList ->
                _searchedPersons.postValue(personList)
            }
        }
    }
}
