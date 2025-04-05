package com.example.introductiontoroom.introduction.data

import com.example.introductiontoroom.introduction.data.model.PersonEntity
import com.example.ui_compose.dataaddres.model.AddressResponse
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    suspend fun insertPerson(personEntity: PersonEntity)
    suspend fun deletePerson(personEntity: PersonEntity)
    suspend fun updatePerson(personEntity: PersonEntity)
    suspend fun deletePersonById(pId: Int)
    fun getAllPerson(): Flow<List<PersonEntity>>
    fun getSearchedData(query: String): Flow<List<PersonEntity>>

    // Métodos para integração com a API de endereços
    suspend fun fetchAddressFromApi(cep: String): AddressResponse?
    suspend fun fetchAddressesFromApi(): Flow<List<AddressResponse>>
    suspend fun insertAddressesFromApi(addressList: List<AddressResponse>)

}
