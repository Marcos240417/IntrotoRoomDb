package com.example.ui_compose.dataaddres

import com.example.introductiontoroom.introduction.data.PersonDao
import com.example.introductiontoroom.introduction.data.model.PersonEntity
import com.example.ui_compose.dataaddres.model.AddressResponse

class AddressRepository(
    private val addressService: AddressService,
    private val personDao: PersonDao
) {


    // Método para atualizar ou salvar o registro no Room
    suspend fun updatePersonInRoom(personEntity: PersonEntity) {
        personDao.updatePerson(personEntity) // Chama o método no DAO
    }

    // Comunicação com a API para buscar endereço
    suspend fun findAddress(cep: String): AddressResponse {
        return addressService.findAddress(cep)
    }


}
