package com.example.introductiontoroom.di

import com.example.introductiontoroom.data.AppDatabase
import com.example.introductiontoroom.data.PersonRepositoryImpl
import com.example.introductiontoroom.viewmodel.PersonViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    // Banco de Dados
    single { AppDatabase.Companion.getDatabase(get()) } // Passa o contexto para criar o banco
    single { get<AppDatabase>().personDao() } // Instância do DAO

    // Repositório
    singleOf(::PersonRepositoryImpl) // Cria o repositório com a referência direta ao construtor

    // ViewModel
    viewModelOf(::PersonViewModel) // Cria o ViewModel com a referência direta ao construtor

    /*single { PersonRepository(get()) } // Injetando o PersonRepository (assumindo que ele precisa do contexto ou de algum outro parâmetro)
    viewModel { PersonViewModel(get()) } // Injetando o PersonViewModel com a dependência do PersonRepository
*/
}
