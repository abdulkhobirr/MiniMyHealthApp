package com.example.myhealthdiary.di

import com.example.myhealthdiary.data.user.UserDataStore
import com.example.myhealthdiary.data.user.UserRepository
import com.example.myhealthdiary.utils.data.AppDatabase
import com.example.myhealthdiary.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userModule = module {
    single {
        AppDatabase.getDatabase(get()).userDao()
    }

    single<UserRepository> { UserDataStore(get()) }

    viewModel { LoginViewModel(get(), get()) }
}