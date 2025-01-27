package com.example.introductiontoroom.viewadapter

import android.content.Context
import com.example.introductiontoroom.data.AppDatabase
import com.example.introductiontoroom.data.Person
import kotlinx.coroutines.flow.Flow

class PersonRepository(private val context: Context) {

    private val db: AppDatabase = AppDatabase.getDatabase(context)

    suspend fun insertPerson(person: Person) = db.personDao().insertPerson(person)
    suspend fun deletePerson(person: Person) = db.personDao().deletePerson(person)
    suspend fun updatePerson(person: Person) = db.personDao().updatePerson(person)
    suspend fun deletePersonById(person: Person) = db.personDao().deletePersonById(person.pId) // Corrigido aqui

    fun getAllPerson(): Flow<List<Person>> = db.personDao().getAllDatta()
    fun getSearchedData(query: String): Flow<List<Person>> = db.personDao().getSearchedData(query)
}
