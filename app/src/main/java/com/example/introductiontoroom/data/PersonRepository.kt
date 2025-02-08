package com.example.introductiontoroom.data

import android.content.Context
import com.example.introductiontoroom.data.model.PersonEntity
import kotlinx.coroutines.flow.Flow

class PersonRepository(private val context: Context) {

    private val db: AppDatabase = AppDatabase.getDatabase(context)

    suspend fun insertPerson(personEntity: PersonEntity) = db.personDao().insertPerson(personEntity)
    suspend fun deletePerson(personEntity: PersonEntity) = db.personDao().deletePerson(personEntity)
    suspend fun updatePerson(personEntity: PersonEntity) = db.personDao().updatePerson(personEntity)
    suspend fun deletePersonById(personEntity: PersonEntity) = db.personDao().deletePersonById(personEntity.pId) // Corrigido aqui

    fun getAllPerson(): Flow<List<PersonEntity>> = db.personDao().getAllDatta()
    fun getSearchedData(query: String): Flow<List<PersonEntity>> = db.personDao().getSearchedData(query)
}
