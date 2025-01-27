package com.example.introductiontoroom.viewadapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PersonViewModelFactory(
    private val personRepository: PersonRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonViewModel::class.java)) {
            return PersonViewModel(personRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
