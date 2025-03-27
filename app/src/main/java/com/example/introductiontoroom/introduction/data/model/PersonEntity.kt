package com.example.introductiontoroom.introduction.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true) val pId: Int,
    @ColumnInfo("person_name") val name: String,
    @ColumnInfo("person_date_birth") val dateBirth:String,
    @ColumnInfo("person_nsus") val nsus: String,
    @ColumnInfo("person_cep") val cep: String,
    @ColumnInfo("person_logradouro") val logradouro: String,
    @ColumnInfo("person_number") val number: String,
    @ColumnInfo("person_bairro") val bairro: String,
    @ColumnInfo("person_cidade") val cidade: String,
    @ColumnInfo("person_estado") val estado: String,
    @ColumnInfo("person_sexo") val sexo: String,
    @ColumnInfo("person_marital_status") val maritalStatus: String,
    @ColumnInfo("person_nationality") val nationality: String,
    @ColumnInfo("person_identity_rg") val identityRG: String,
    @ColumnInfo("person_identity_cpf") val identityCPF: String,
    @ColumnInfo("person_phone") val phone: String,
    @ColumnInfo("person_email") val email: String?,
    val isLoading: Boolean = false,
    val isError: Boolean = false,

)


