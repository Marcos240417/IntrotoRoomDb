package com.example.introductiontoroom.di

import com.example.introductiontoroom.data.AppDatabase
import com.example.introductiontoroom.data.PersonDao
import com.example.introductiontoroom.data.PersonRepository
import com.example.introductiontoroom.data.PersonRepositoryImpl
import com.example.introductiontoroom.ui_compose.network.AddressService
import com.example.introductiontoroom.ui_compose.repositorys.AddressRepository
import com.example.introductiontoroom.viewmodel.AddressViewModel
import com.example.introductiontoroom.viewmodel.PersonViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    // Banco de Dados
    single { AppDatabase.getDatabase(androidContext()) }
    single<PersonDao> { get<AppDatabase>().personDao() }


    // Repositório
    single<PersonRepository> { PersonRepositoryImpl(get<PersonDao>()) }

    // ViewModels
    viewModel { PersonViewModel(get<PersonRepository>()) }


}


val cepNetworkModule = module {
    // Repositório de Endereço
    singleOf(::AddressRepository) // Registrando o repositório

    // ViewModel de Endereço
    viewModel { AddressViewModel(get()) }


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
                .client(cliente) // Adiciona o cliente OkHttp configurado
                .build()

            retrofit.create(AddressService::class.java) // Cria a implementação da interface AddressService
        }

    }





