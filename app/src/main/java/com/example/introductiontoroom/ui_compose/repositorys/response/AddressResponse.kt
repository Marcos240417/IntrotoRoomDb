package com.example.introductiontoroom.ui_compose.repositorys.response

import com.example.introductiontoroom.ui_compose.repositorys.state.AddressFormUiState
import com.squareup.moshi.Json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AddressResponse(
    @SerialName("logradouro")
    val logradouro: String = "",

    @SerialName("bairro")
    val bairro: String = "",

    @SerialName("localidade")
    @field:Json(name = "localidade")
    val cidade: String = "",

    @SerialName("uf")
    @field:Json(name = "uf")
    val estado: String = ""

) {
    fun toAddressFormUiState() = AddressFormUiState(
        logradouro = logradouro,
        bairro = bairro,
        cidade = cidade,
        estado = estado,
        isLoading = false,
        isError = false
    )
}
