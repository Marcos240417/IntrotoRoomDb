package com.example.introductiontoroom

import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Definir AppDatabase para ser acessado em toda a aplicação
    single { AppDatabase.getDatabase(androidApplication()) }

    // Definir PersonRepository, passando o Context da aplicação
    single { PersonRepository(get()) } // Aqui 'get()' vai buscar o Context via androidApplication()

    // Definir o PersonViewModel, injetando PersonRepository
    viewModel { PersonViewModel(get()) }
}

