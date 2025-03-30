plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    alias(libs.plugins.ksp.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.introductiontoroom"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.introductiontoroom"
        minSdk = 25
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true  // Ativa o Compose no projeto
        viewBinding = true  // Ativa o ViewBinding
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.firebase.crashlytics.buildtools)
    implementation(libs.androidx.baselibrary)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.room.ktx)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    ksp(libs.androidx.room.compiler)


    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    // LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)
    // Annotation processor
    ksp(libs.androidx.lifecycle.compiler)

    // Coroutines Core
    implementation(libs.kotlinx.coroutines.core)
    // Coroutines Android
    implementation(libs.kotlinx.coroutines.android)

    // Koin Core: contém o núcleo do Koin para injeção de dependências
    implementation(libs.koin.core)
    // Koin Android: suporte para Android e ViewModel
    implementation(libs.io.insert.koin)

    // Coroutines para Retrofit com coroutines
    implementation (libs.adapter.rxjava2)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // define a BOM and its version
    implementation(platform(libs.okhttp.bom))

    // define any required OkHttp artifacts without version
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Moshi (para serialização JSON)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.converter.moshi)


    // KotlinX Serialization
    implementation(libs.kotlinx.serialization.json)

    // Para usar o Koin com Jetpack Compose
    implementation (libs.insert.koin.koin.androidx.compose)
    implementation (libs.io.insert.koin ) // Para Koin com Androidndroid)  // Para Koin com Android

    // Opcional: Cliente OkHttp para log das requisições
    implementation(libs.okhttp3.logging.interceptor)
    // OkHttp
    implementation (libs.okhttp3.okhttp)
    // Firebase Crashlytics
    implementation(libs.firebase.crashlytics)


}