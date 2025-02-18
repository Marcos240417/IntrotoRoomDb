package com.example.introductiontoroom.di

import com.example.introductiontoroom.data.AppDatabase
import com.example.introductiontoroom.data.PersonDao
import com.example.introductiontoroom.data.PersonRepository
import com.example.introductiontoroom.data.PersonRepositoryImpl
import com.example.introductiontoroom.viewmodel.PersonViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Banco de Dados
    single { AppDatabase.Companion.getDatabase(androidContext()) } // Passa o contexto para criar o banco
    single<PersonDao> { get<AppDatabase>().personDao() } // Instância do DAO

    // Repositório
    factory<PersonRepository> { PersonRepositoryImpl(get<PersonDao>()) } // Instância do repositório

    // ViewModel
    viewModel { PersonViewModel(get<PersonRepository>()) } // Instância do ViewModel
}
