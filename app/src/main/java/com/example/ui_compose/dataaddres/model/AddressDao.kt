package com.example.ui_compose.dataaddres.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.introductiontoroom.introduction.data.model.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDao {

    // Método para inserir um endereço no banco de dados
    // O parâmetro `onConflict = OnConflictStrategy.REPLACE` garante que,
    // em caso de conflito (como chaves primárias duplicadas), o novo registro substitua o existente
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddress(addressEntity: PersonEntity)

    // Método para deletar um endereço do banco de dados Room
    // O registro será identificado e removido com base nos campos preenchidos no objeto `addressEntity`
    @Delete
    suspend fun deleteAddress(addressEntity: PersonEntity)

    // Método para atualizar um endereço existente no banco de dados
    // O registro a ser atualizado é identificado pela chave primária no objeto `addressEntity`
    @Update
    suspend fun updateAddress(addressEntity: PersonEntity)

    // Método personalizado para deletar um endereço pelo seu ID
    // O parâmetro `:addressId` será substituído dinamicamente pelo valor passado para o método
    @Query("DELETE FROM person_table WHERE pId = :addressId")
    suspend fun deleteAddressById(addressId: Int)

    // Método para recuperar todos os endereços armazenados na tabela `address_table`
    // Os resultados serão ordenados de forma decrescente pelo campo `id`
    @Query("SELECT * FROM person_table ORDER BY pId DESC")
    fun getAllAddresses(): Flow<List<PersonEntity>>

    // Método para buscar registros que correspondam a um termo de pesquisa
    // O operador `LIKE` permite buscas parciais, podendo ser combinado com `%` como curinga
    // Exemplo: `query = "%Rua%"` retornaria todos os endereços onde o logradouro contém "Rua"
    @Query("SELECT * FROM person_table WHERE person_logradouro LIKE :query OR person_cep LIKE :query")
    fun getSearchedAddresses(query: String): Flow<List<PersonEntity>>
}
