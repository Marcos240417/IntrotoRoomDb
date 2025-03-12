package com.example.introductiontoroom.ui_compose.repositorys

import com.example.introductiontoroom.ui_compose.network.AddressService
import com.example.introductiontoroom.ui_compose.repositorys.response.AddressResponse

class AddressRepository(

    private val addressService: AddressService
) {

    suspend fun findAddress(cep: String): AddressResponse {

        return addressService.findAddress(cep)
    }
}