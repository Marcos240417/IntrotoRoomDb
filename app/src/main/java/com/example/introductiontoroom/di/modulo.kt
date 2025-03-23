package com.example.introductiontoroom.di

import com.example.introductiontoroom.introduction.data.AppDatabase
import com.example.introductiontoroom.introduction.data.PersonDao
import com.example.introductiontoroom.introduction.data.PersonRepository
import com.example.introductiontoroom.introduction.data.PersonRepositoryImpl
import com.example.introductiontoroom.introduction.viewmodel.PersonViewModel
import com.example.ui_compose.dataaddres.AddressRepository
import com.example.ui_compose.dataaddres.model.network.AddressService
import com.example.ui_compose.ui.AddressViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single { AppDatabase.getDatabase(androidContext()) } // Instância do banco de dados
    single { get<AppDatabase>().personDao() } // DAO da tabela Person
    single <PersonRepository> { PersonRepositoryImpl(get<PersonDao>())} // Repositório para Person
    viewModel { PersonViewModel(get()) } // ViewModel para Person
}


val cepNetworkModule = module {
    single {
        AddressRepository(get<AddressService>()) // AddressRepository depende do AddressService
    }

    viewModel {
        AddressViewModel(get()) // ViewModel depende do AddressRepository
    }
}




    val networkModule = module {
        // Retrofit e OkHttpClient
        single {
            val baseUrl = "https://viacep.com.br/ws/"
            val cliente = OkHttpClient.Builder()
                .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY // Habilita logging completo
                })
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl) // Define a URL base da API
                .addConverterFactory(
                    MoshiConverterFactory.create(
                        Moshi.Builder()
                            .add(KotlinJsonAdapterFactory()) // Configura Moshi para Kotlin
                            .build()
                    )
                )
                .client(cliente) // Usa o OkHttpClient configurado acima
                .build()

            retrofit.create(AddressService::class.java) // Cria a implementação da interface AddressService
        }

    }





