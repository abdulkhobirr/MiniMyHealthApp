package com.example.myhealthdiary.di

import com.example.myhealthdiary.BuildConfig.BASE_URL
import com.example.myhealthdiary.data.diagnosischat.DiagnosisChatDataStore
import com.example.myhealthdiary.data.diagnosischat.DiagnosisChatRepository
import com.example.myhealthdiary.data.diagnosischat.remote.DiagnosisChatApi
import com.example.myhealthdiary.data.diagnosischat.remote.DiagnosisChatApiClient
import com.example.myhealthdiary.utils.data.ApiService
import com.example.myhealthdiary.viewmodel.DiagnosisChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val chatModule = module {
    single {
        ApiService.createReactiveService(
            DiagnosisChatApiClient::class.java,
            get(),
            get(named(BASE_URL))
        )
    }

    single { DiagnosisChatApi(get()) }

    single<DiagnosisChatRepository> { DiagnosisChatDataStore(get()) }

    viewModel { DiagnosisChatViewModel(get(), get()) }
}