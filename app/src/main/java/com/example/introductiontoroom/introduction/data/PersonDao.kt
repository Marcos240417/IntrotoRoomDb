package com.example.introductiontoroom.introduction.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.introductiontoroom.introduction.data.model.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(personEntity: PersonEntity)

    @Update
    suspend fun updatePerson(personEntity: PersonEntity)

    @Delete
    suspend fun deletePerson(personEntity: PersonEntity)

    @Query("DELETE FROM person_table WHERE pId = :pId")
    suspend fun deletePersonById(pId: Int)

    @Query("SELECT * FROM person_table")
    fun getAllDatta(): Flow<List<PersonEntity>>

    //Procura por ambos inicio e fim da String.
    @Query("SELECT * FROM person_table WHERE person_name LIKE '%' || :query || '%' or person_date_birth LIKE '%' || :query || '%' or person_nsus LIKE '%' || :query || '%'")
    fun getSearchedData(query: String): Flow<List<PersonEntity>>

}