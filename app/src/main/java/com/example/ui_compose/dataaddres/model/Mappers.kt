package com.example.ui_compose.dataaddres.model

import com.example.introductiontoroom.introduction.data.model.PersonEntity

object AddressMapper {
    fun toPersonEntity(addressResponse: AddressResponse, person: PersonEntity): PersonEntity {
        return person.copy(
            cep = addressResponse.cep,
            logradouro = addressResponse.logradouro,
            bairro = addressResponse.bairro,
            cidade = addressResponse.localidade,
            estado = addressResponse.estado
        )
    }
}

