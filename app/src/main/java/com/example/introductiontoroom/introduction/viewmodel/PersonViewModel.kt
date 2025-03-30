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
import android.util.Log

class PersonViewModel(
    private val personRepository: PersonRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _searchedPersons = MutableLiveData<List<PersonEntity>>()
    val searchedPersons: LiveData<List<PersonEntity>> get() = _searchedPersons

    init {
        getAllPersons()
    }

    private fun getAllPersons() {
        viewModelScope.launch(dispatcher) {
            try {
                personRepository.getAllPerson().collect { personList ->
                    _searchedPersons.postValue(personList)
                }
            } catch (e: Exception) {
                Log.e("PersonViewModel", "Erro ao obter todas as pessoas", e)
            }
        }
    }

    fun addPerson(personEntity: PersonEntity) {
        viewModelScope.launch(dispatcher) {
            try {
                personRepository.insertPerson(personEntity)
            } catch (e: Exception) {
                Log.e("PersonViewModel", "Erro ao adicionar pessoa", e)
            }
        }
    }

    fun deletePerson(personEntity: PersonEntity) {
        viewModelScope.launch(dispatcher) {
            try {
                personRepository.deletePerson(personEntity)
            } catch (e: Exception) {
                Log.e("PersonViewModel", "Erro ao deletar pessoa", e)
            }
        }
    }

    fun updatePerson(personEntity: PersonEntity) {
        viewModelScope.launch(dispatcher) {
            try {
                personRepository.updatePerson(personEntity)
            } catch (e: Exception) {
                Log.e("PersonViewModel", "Erro ao atualizar pessoa", e)
            }
        }
    }

    fun getSearchedData(query: String = "") {
        viewModelScope.launch(dispatcher) {
            try {
                personRepository.getSearchedData(query).collect { personList ->
                    _searchedPersons.postValue(personList)
                }
            } catch (e: Exception) {
                Log.e("PersonViewModel", "Erro ao buscar pessoas", e)
            }
        }
    }
}
