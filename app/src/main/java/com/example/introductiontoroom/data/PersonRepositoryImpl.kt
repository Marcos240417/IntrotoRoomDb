package com.example.introductiontoroom.data

import com.example.introductiontoroom.data.model.PersonEntity
import kotlinx.coroutines.flow.Flow

class PersonRepositoryImpl(private val personDao: PersonDao) : PersonRepository {

    override suspend fun insertPerson(personEntity: PersonEntity) {
        personDao.insertPerson(personEntity)
    }

    override suspend fun deletePerson(personEntity: PersonEntity) {
        personDao.deletePerson(personEntity)
    }

    override suspend fun updatePerson(personEntity: PersonEntity) {
        personDao.updatePerson(personEntity)
    }

    override suspend fun deletePersonById(personEntity: PersonEntity) {
        personDao.deletePersonById(personEntity.pId) // Corrigido aqui
    }

    override fun getAllPerson(): Flow<List<PersonEntity>> {
        return personDao.getAllDatta()
    }

    override fun getSearchedData(query: String): Flow<List<PersonEntity>> {
        return personDao.getSearchedData(query)
    }
}
