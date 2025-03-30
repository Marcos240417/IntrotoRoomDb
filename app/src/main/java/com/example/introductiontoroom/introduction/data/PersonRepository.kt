package com.example.introductiontoroom.introduction.data

import com.example.introductiontoroom.introduction.data.model.PersonEntity
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    suspend fun insertPerson(personEntity: PersonEntity)
    suspend fun deletePerson(personEntity: PersonEntity)
    suspend fun updatePerson(personEntity: PersonEntity)
    suspend fun deletePersonById(pId: Int)
    fun getAllPerson(): Flow<List<PersonEntity>>
    fun getSearchedData(query: String): Flow<List<PersonEntity>>

    // Adicionar integração com Retrofit
    suspend fun fetchPeopleFromApi(): Flow<List<PersonEntity>>
    suspend fun searchPeopleFromApi(query: String): Flow<List<PersonEntity>>
   // suspend fun insertPeopleFromApi(insertPerson: String): Flow<List<PersonEntity>>

    // Alterar a assinatura da função para retornar Flow<Unit>
    suspend fun insertPeopleFromApi(insertPerson: String): Flow<Unit>

}
