package com.example.introductiontoroom.data

import com.example.introductiontoroom.data.model.PersonEntity
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    suspend fun insertPerson(personEntity: PersonEntity)
    suspend fun deletePerson(personEntity: PersonEntity)
    suspend fun updatePerson(personEntity: PersonEntity)
    suspend fun deletePersonById(personEntity: PersonEntity)
    fun getAllPerson(): Flow<List<PersonEntity>>
    fun getSearchedData(query: String): Flow<List<PersonEntity>>

}