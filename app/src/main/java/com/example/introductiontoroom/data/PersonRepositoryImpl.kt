package com.example.introductiontoroom.data

import com.example.introductiontoroom.data.model.PersonEntity
import kotlinx.coroutines.flow.Flow

class PersonRepositoryImpl(private val personDao: PersonDao) {

    suspend fun insertPerson(personEntity: PersonEntity) {
        personDao.insertPerson(personEntity)
    }

    suspend fun deletePerson(personEntity: PersonEntity) {
        personDao.deletePerson(personEntity)
    }

    suspend fun updatePerson(personEntity: PersonEntity) {
        personDao.updatePerson(personEntity)
    }

    suspend fun deletePersonById(personEntity: PersonEntity) {
        personDao.deletePersonById(personEntity.pId) // Corrigido aqui
    }

    fun getAllPerson(): Flow<List<PersonEntity>> {
        return personDao.getAllDatta()
    }

    fun getSearchedData(query: String): Flow<List<PersonEntity>> {
        return personDao.getSearchedData(query)
    }
}
