package com.example.introductiontoroom.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                appModule,       // Módulo do banco de dados e Person
                networkModule,   // Módulo de Rede (Retrofit, OkHttp, AddressService)
                cepNetworkModule // Módulo de Endereço (AddressRepository, AddressViewModel)
            )
        }
    }
}