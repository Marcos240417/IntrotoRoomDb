package com.example.introductiontoroom.introduction.data

import android.util.Log
import com.example.introductiontoroom.introduction.data.model.PersonEntity
import kotlinx.coroutines.flow.Flow

class PersonRepositoryImpl(
    private val personDao: PersonDao,
    //private val personApi: PersonApi // Banco de dados local
) : PersonRepository
{

    override suspend fun insertPerson(personEntity: PersonEntity) {
      try {
          personDao.insertPerson(personEntity)
      }
      catch (e: Exception) {
          Log.e("PersonRepositoryImpl", "Erro ao inserir pessoa", e)
      }
    }

    override suspend fun deletePerson(personEntity: PersonEntity) {
       try {
           personDao.deletePerson(personEntity)
       }
       catch (e: Exception) {
           Log.e("PersonRepositoryImpl", "Erro ao deletar pessoa", e)
       }
    }

    override suspend fun updatePerson(personEntity: PersonEntity) {
        try {
            personDao.updatePerson(personEntity)
        }
        catch (e: Exception) {
            Log.e("PersonRepositoryImpl", "Erro ao atualizar pessoa", e)
        }
    }

    override suspend fun deletePersonById(pId: Int) {
        try {
            personDao.deletePersonById(pId) // Corrigido aqui
        }
        catch (e: Exception) {
            Log.e("PersonRepositoryImpl", "Erro ao deletar pessoa por ID", e)
        }
    }

    override fun getAllPerson(): Flow<List<PersonEntity>> {
        try {
            return personDao.getAllData()
        }
        catch (e: Exception) {
            Log.e("PersonRepositoryImpl", "Erro ao obter todas as pessoas", e)
            throw e
        }
    }

    override fun getSearchedData(query: String): Flow<List<PersonEntity>> {
        try {
            return personDao.getSearchedData(query)
        }
        catch (e: Exception) {
            Log.e("PersonRepositoryImpl", "Erro ao buscar pessoas", e)
            throw e
        }
    }

    // Métodos da API (não implementados)
    override suspend fun fetchPeopleFromApi(): Flow<List<PersonEntity>> {
        throw NotImplementedError("fetchPeopleFromApi não foi implementado")
    }

    override suspend fun searchPeopleFromApi(query: String): Flow<List<PersonEntity>> {
        throw NotImplementedError("searchPeopleFromApi não foi implementado")
    }

    override suspend fun insertPeopleFromApi(insertPerson: String): Flow<Unit> {
        throw NotImplementedError("insertPeopleFromApi não foi implementado")
    }

}
