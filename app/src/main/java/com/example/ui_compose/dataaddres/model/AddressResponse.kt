package com.example.ui_compose.dataaddres.model

import com.example.introductiontoroom.introduction.data.model.PersonEntity
import com.squareup.moshi.Json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressResponse(
    @SerialName("logradouro")
    val logradouro: String = "",
    @SerialName("bairro")
    val bairro: String = "",
    @SerialName("localidade")
    @field:Json(name = "localidade")
    val localidade: String = "",
    @SerialName("uf")
    @field:Json(name = "uf")
    val estado: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val data: ArrayList<PersonEntity>
)


