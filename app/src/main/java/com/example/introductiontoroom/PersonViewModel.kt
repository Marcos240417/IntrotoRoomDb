package com.example.introductiontoroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonViewModel(private val personRepository: PersonRepository) : ViewModel() {

    // Função para adicionar uma pessoa
    fun addPerson(person: Person) {
        viewModelScope.launch(Dispatchers.IO) {
            personRepository.insertPerson(person)
        }
    }

    // Função para excluir uma pessoa
    fun deletePerson(person: Person) {  // Renomeado para deletePerson
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
    fun deletePersonById(person: Person) {  // Alterado para receber o ID
        viewModelScope.launch(Dispatchers.IO) {
            personRepository.deletePersonById(person)  // Passa o ID para o repositório
        }
    }

    // Função para obter todos os registros
    fun getAllPerson() = personRepository.getAllPerson()

    // Função para procurar uma pessoa com base no nome, idade ou cidade
    fun getSearchedData(query: String?) = personRepository.getSearchedData(query)
}
