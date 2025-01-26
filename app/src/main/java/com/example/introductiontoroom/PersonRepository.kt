package com.example.introductiontoroom

import android.content.Context

class PersonRepository(private val context: Context) {

    private val db: AppDatabase = AppDatabase.getDatabase(context)

    suspend fun insertPerson(person: Person) = db.personDao().insertPerson(person)
    suspend fun deletePerson(person: Person) = db.personDao().deletePerson(person)
    suspend fun updatePerson(person: Person) = db.personDao().updatePerson(person)
    suspend fun deletePersonById(person: Person) = db.personDao().deletePersonById(person.pId) // Corrigido aqui

    fun getAllPerson() = db.personDao().getAllDatta()
    fun getSearchedData(query: String?) = db.personDao().getSearchedData(query.toString())
}
