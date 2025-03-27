package com.example.ui_compose.dataaddres


import com.example.introductiontoroom.introduction.data.model.PersonEntity
import com.example.ui_compose.dataaddres.model.AddressResponse
import kotlinx.coroutines.flow.Flow

interface AddressRepository {
    // Funcionalidades do Room
    suspend fun insertAddress(addressEntity: PersonEntity) // Insere endereço
    suspend fun deleteAddress(addressEntity: PersonEntity) // Deleta endereço
    suspend fun updateAddress(addressEntity: PersonEntity) // Atualiza endereço
    suspend fun deleteAddressById(addressId: Int) // Deleta endereço pelo ID
    fun getAllAddresses(): Flow<List<PersonEntity>> // Retorna todos os endereços
    fun getSearchedAddresses(query: String): Flow<List<PersonEntity>> // Busca endereços por consulta

    // Funcionalidades do Retrofit
    suspend fun fetchAddressFromApi(cep: String): AddressResponse? // Busca endereço via API por CEP
    suspend fun fetchAddressesFromApi(): Flow<List<AddressResponse>> // Busca vários endereços via API
    suspend fun insertAddressesFromApi(addressList: List<AddressResponse>) // Insere dados da API no Room
}
