package com.example.myhealthdiary.di

import com.example.myhealthdiary.utils.pref.PrefManager
import com.example.myhealthdiary.utils.pref.PreferenceManagerImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val PREFERENCE_NAME = "preference_name"

val preferenceModule = module {

    single(named(PREFERENCE_NAME)) { "Pref Name" }

    single<PrefManager> { PreferenceManagerImpl(get(), get(named(PREFERENCE_NAME))) }
}