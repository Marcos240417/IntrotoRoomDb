package com.example.introductiontoroom.introduction.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.introductiontoroom.introduction.data.PersonRepository
import com.example.introductiontoroom.introduction.data.model.PersonEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonViewModel(
    private val personRepository: PersonRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _searchedPersons = MutableLiveData<List<PersonEntity>>()
    val searchedPersons: LiveData<List<PersonEntity>> get() = _searchedPersons

    init {
        // Inicializa com a lista de pessoas do banco local
        getAllPersons()
    }

    // Obtém todas as pessoas do banco local (Room)
    private fun getAllPersons() {
        viewModelScope.launch(dispatcher) {
            personRepository.getAllPerson().collect { personList ->
                _searchedPersons.postValue(personList)
            }
        }
    }

    fun addPerson(personEntity: PersonEntity) {
        viewModelScope.launch(dispatcher) {
            personRepository.insertPerson(personEntity)
        }
    }

    fun deletePerson(personEntity: PersonEntity) {
        viewModelScope.launch(dispatcher) {
            personRepository.deletePerson(personEntity)
        }
    }

    fun updatePerson(personEntity: PersonEntity) {
        viewModelScope.launch(dispatcher) {
            personRepository.updatePerson(personEntity)
        }
    }

    /*fun deletePersonById(personEntity: PersonEntity) {
        viewModelScope.launch(dispatcher) {
            personRepository.deletePersonById(personEntity)
        }
    }*/

    fun getSearchedData(query: String = "") {
        viewModelScope.launch(dispatcher) {
            personRepository.getSearchedData(query).collect { personList ->
                _searchedPersons.postValue(personList)
            }
        }
    }
}
