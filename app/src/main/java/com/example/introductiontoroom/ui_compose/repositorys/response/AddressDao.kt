package com.example.introductiontoroom.ui_compose.repositorys.response

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.introductiontoroom.ui_compose.repositorys.state.AddressFormUiState

@Dao
interface AddressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAddress(endereco: Registrationform)

    @Query("SELECT * FROM endereco WHERE cep = :cep")
    suspend fun buscarEnderecoPorCep(cep: String): AddressFormUiState?
}