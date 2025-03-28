package com.example.introductiontoroom.introduction.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true) val pId: Int,
    @ColumnInfo(name = "person_name") val name: String,
    @ColumnInfo(name = "person_date_birth") val dateBirth: String,
    @ColumnInfo(name = "person_nsus") val nsus: String,
    @ColumnInfo(name = "person_cep") val cep: String = "",
    @ColumnInfo(name = "person_logradouro") val logradouro: String = "",
    @ColumnInfo(name = "person_number") val number: String = "",
    @ColumnInfo(name = "person_bairro") val bairro: String = "",
    @ColumnInfo(name = "person_cidade") val cidade: String = "",
    @ColumnInfo(name = "person_estado") val estado: String = "",
    @ColumnInfo(name = "person_sexo") val sexo: String = "",
    @ColumnInfo(name = "person_marital_status") val maritalStatus: String = "",
    @ColumnInfo(name = "person_nationality") val nationality: String = "",
    @ColumnInfo(name = "person_identity_rg") val identityRG: String = "",
    @ColumnInfo(name = "person_identity_cpf") val identityCPF: String = "",
    @ColumnInfo(name = "person_phone") val phone: String = "",
    @ColumnInfo(name = "person_email") val email: String? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,

)


