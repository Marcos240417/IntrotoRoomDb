package com.example.introductiontoroom.data

import com.example.introductiontoroom.viewadapter.PersonRepository
import com.example.introductiontoroom.viewadapter.PersonViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    // Banco de Dados
    single { AppDatabase.getDatabase(get()) } // Passa o contexto para criar o banco
    single { get<AppDatabase>().personDao() } // Instância do DAO

    // Repositório
    singleOf(::PersonRepository) // Cria o repositório com a referência direta ao construtor

    // ViewModel
    viewModelOf(::PersonViewModel) // Cria o ViewModel com a referência direta ao construtor

    /*single { PersonRepository(get()) } // Injetando o PersonRepository (assumindo que ele precisa do contexto ou de algum outro parâmetro)
    viewModel { PersonViewModel(get()) } // Injetando o PersonViewModel com a dependência do PersonRepository
*/
}
