package com.example.introductiontoroom.introduction.data

import android.util.Log
import com.example.introductiontoroom.introduction.data.model.PersonEntity
import com.example.ui_compose.dataaddres.model.AddressResponse
import com.example.ui_compose.dataaddres.model.network.AddressService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PersonRepositoryImpl(
    private val personDao: PersonDao,
    private val addressService: AddressService // Banco de dados local
) : PersonRepository
{

    override suspend fun insertPerson(personEntity: PersonEntity) {
      try {
          personDao.insertPerson(personEntity)
      }
      catch (e: Exception) {
          Log.e("PersonRepositoryImpl", "Erro ao inserir pessoa", e)
      }
    }

    override suspend fun deletePerson(personEntity: PersonEntity) {
       try {
           personDao.deletePerson(personEntity)
       }
       catch (e: Exception) {
           Log.e("PersonRepositoryImpl", "Erro ao deletar pessoa", e)
       }
    }

    override suspend fun updatePerson(personEntity: PersonEntity) {
        try {
            personDao.updatePerson(personEntity)
        }
        catch (e: Exception) {
            Log.e("PersonRepositoryImpl", "Erro ao atualizar pessoa", e)
        }
    }

    override suspend fun deletePersonById(pId: Int) {
        try {
            personDao.deletePersonById(pId) // Corrigido aqui
        }
        catch (e: Exception) {
            Log.e("PersonRepositoryImpl", "Erro ao deletar pessoa por ID", e)
        }
    }

    override fun getAllPerson(): Flow<List<PersonEntity>> {
        try {
            return personDao.getAllData()
        }
        catch (e: Exception) {
            Log.e("PersonRepositoryImpl", "Erro ao obter todas as pessoas", e)
            throw e
        }
    }

    override fun getSearchedData(query: String): Flow<List<PersonEntity>> {
        try {
            return personDao.getSearchedData(query)
        }
        catch (e: Exception) {
            Log.e("PersonRepositoryImpl", "Erro ao buscar pessoas", e)
            throw e
        }
    }

    /** Busca um endereço na API pelo CEP */
    override suspend fun fetchAddressFromApi(cep: String): AddressResponse? {
        return try {
            addressService.findAddress(cep)
        } catch (e: Exception) {
            Log.e("PersonRepositoryImpl", "Erro ao buscar endereço na API", e)
            null
        }
    }

    /** Busca vários endereços na API */
    override suspend fun fetchAddressesFromApi(): Flow<List<AddressResponse>> {
        return flow {
            try {
                val response = listOf<AddressResponse>() // Simulação da resposta da API
                emit(response)
            } catch (e: Exception) {
                Log.e("PersonRepositoryImpl", "Erro ao buscar endereços na API", e)
                emit(emptyList())
            }
        }
    }

    /** Insere endereços retornados da API no banco de dados */
    override suspend fun insertAddressesFromApi(addressList: List<AddressResponse>) {
        val addressEntities = addressList.map { response ->
            PersonEntity(
                pId = 0,
                name = "",
                dateBirth = "",
                nsus = "",
                cep = response.cep,
                logradouro = response.logradouro,
                number = "",
                bairro = response.bairro,
                cidade = response.localidade,
                estado = response.estado,
                sexo = "",
                maritalStatus = "",
                nationality = "",
                identityRG = "",
                identityCPF = "",
                phone = "",
                email = ""
            )
        }

        try {
            addressEntities.forEach { entity ->
                personDao.insertPerson(entity)
            }
        } catch (e: Exception) {
            Log.e("PersonRepositoryImpl", "Erro ao inserir endereços da API no Room", e)
        }
    }

}
