package com.example.ui_compose.dataaddres

import com.example.introductiontoroom.introduction.data.model.PersonEntity
import com.example.ui_compose.dataaddres.model.AddressDao
import com.example.ui_compose.dataaddres.model.AddressResponse
import com.example.ui_compose.dataaddres.model.network.AddressService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddressRepositoryImpl(
    private val addressDao: AddressDao,
    private val addressService: AddressService
) : AddressRepository {

    // Insere um endereço no Room
    override suspend fun insertAddress(addressEntity: PersonEntity) {
        addressDao.insertAddress(addressEntity)
    }

    // Deleta um endereço no Room
    override suspend fun deleteAddress(addressEntity: PersonEntity) {
        addressDao.deleteAddress(addressEntity)
    }

    // Atualiza um endereço no Room
    override suspend fun updateAddress(addressEntity: PersonEntity) {
        addressDao.updateAddress(addressEntity)
    }

    // Deleta um endereço pelo ID no Room
    override suspend fun deleteAddressById(addressId: Int) {
        addressDao.deleteAddressById(addressId)
    }

    // Retorna todos os endereços no Room
    override fun getAllAddresses(): Flow<List<PersonEntity>> {
        return addressDao.getAllAddresses()
    }

    // Busca endereços no Room com base em um termo de pesquisa
    override fun getSearchedAddresses(query: String): Flow<List<PersonEntity>> {
        return addressDao.getSearchedAddresses(query)
    }

    // Busca um endereço na API pelo CEP
    override suspend fun fetchAddressFromApi(cep: String): AddressResponse? {
        return try {
            addressService.findAddress(cep) // Faz a chamada ao endpoint da API
        } catch (e: Exception) {
            e.printStackTrace() // Log do erro
            null // Retorna nulo em caso de erro
        }
    }

    // Busca vários endereços na API
    override suspend fun fetchAddressesFromApi(): Flow<List<AddressResponse>> {
        return flow {
            try {
                val response = addressService.fetchAddresses()
                emit(response)
            } catch (e: Exception) {
                e.printStackTrace() // Log do erro
                emit(emptyList()) // Retorna lista vazia em caso de erro
            }
        }
    }

    // Insere endereços retornados da API no banco de dados Room
    override suspend fun insertAddressesFromApi(addressList: List<AddressResponse>) {
        val addressEntities = addressList.map { response ->
            PersonEntity(
                pId = 0, // ID auto-gerado pelo Room
                name = "", // Preencha com o nome da pessoa, se aplicável
                dateBirth = "", // Pode ser preenchido posteriormente
                nsus = "",
                cep = response.cep,
                logradouro = response.logradouro, // Verifique se o campo pode ser nulo
                number = "",
                bairro = response.bairro, // Verifique se o campo pode ser nulo
                cidade = response.localidade, // Verifique se o campo pode ser nulo
                estado = response.estado, // Verifique se o campo pode ser nulo
                sexo = "",
                maritalStatus = "",
                nationality = "",
                identityRG = "",
                identityCPF = "",
                phone = "",
                email = ""
            )
        }

        // Inserir cada PersonEntity um por um
        addressEntities.forEach { addressEntity ->
            addressDao.insertAddress(addressEntity)  // Inserindo um item de cada vez
        }
    }
}
