package com.example.introductiontoroom.ui_compose.network

import com.example.introductiontoroom.ui_compose.repositorys.response.AddressResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AddressService {

    @GET("{cep}/json/")
    suspend fun findAddress(@Path("cep") cep: String): AddressResponse

}