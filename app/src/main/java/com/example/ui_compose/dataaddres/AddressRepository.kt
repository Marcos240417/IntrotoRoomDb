package com.example.ui_compose.dataaddres

import com.example.ui_compose.dataaddres.model.AddressResponse

class AddressRepository(
    private val addressService: AddressService
) {

    // Comunicação com a API para buscar endereço
    suspend fun findAddress(cep: String): AddressResponse {
        return addressService.findAddress(cep)
    }


}
