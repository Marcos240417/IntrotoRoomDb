package com.example.ui_compose.dataaddres.model.network

import com.example.ui_compose.dataaddres.AddressService
import com.example.ui_compose.dataaddres.model.AddressResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AddressService : AddressService {

    @GET("{cep}/json/")
    override suspend fun findAddress(@Path("cep") cep: String): AddressResponse

}