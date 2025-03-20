package com.example.introductiontoroom.ui_compose.repositorys.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.introductiontoroom.ui_compose.repositorys.state.AddressFormUiState
import com.squareup.moshi.Json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "endereco")
data class Registrationform(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "cep") val cep: String = "", // Melhor usar String para CEP!
    @ColumnInfo(name = "rua") val rua: String = "",
    @ColumnInfo(name = "bairro") val bairro: String = "",
    @ColumnInfo(name = "cidade") val cidade: String = "",
    @ColumnInfo(name = "estado") val estado: String = "",
    @ColumnInfo(name = "sexo") val Sexo: String = "",
    @ColumnInfo(name = "estado_civil") val maritalstatus: String = "",
    @ColumnInfo(name = "nacionalidade") val nationality: String = "",
    @ColumnInfo(name = "identidadeRG") val identityRG: Int = 0,
    @ColumnInfo(name = "identidadeCPF") val identityCPF: Int? = null,
    @ColumnInfo(name = "celular") val phone: Int = 0,
    @ColumnInfo(name = "email") val email: String = ""
)
fun AddressResponse.toRegistrationform(
    cep: String,
    sexo: String,
    maritalStatus: String,
    nationality: String,
    identityRG: Int,
    identityCPF: Int?,
    phone: Int,
    email: String
): Registrationform {
    return Registrationform(
        cep = cep, // Substitua por uma lógica real para o CEP
        rua = this.logradouro,
        bairro = this.bairro,
        cidade = this.localidade,
        estado = this.estado,
        maritalstatus = maritalStatus,
        nationality = nationality,
        identityRG = identityRG,
        identityCPF = identityCPF,
        Sexo = sexo,
        phone = phone,
        email = email
    )
}


@Serializable
class AddressResponse(

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

    val number: String = "",





) {
    fun toAddressFormUiState() = AddressFormUiState(
        logradouro = logradouro,
        bairro = bairro,
        localidade = localidade,
        estado = estado,
        isLoading = false,
        isError = false
    )
}
