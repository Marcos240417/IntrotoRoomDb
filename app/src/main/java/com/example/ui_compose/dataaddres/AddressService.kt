package com.example.ui_compose.dataaddres

import com.example.ui_compose.dataaddres.model.AddressResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface AddressService {

    @GET("{cep}/json/")
    suspend fun findAddress(@Path("cep") cep: String): AddressResponse

}