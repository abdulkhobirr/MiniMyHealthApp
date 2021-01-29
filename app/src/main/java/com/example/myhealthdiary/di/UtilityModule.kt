package com.example.myhealthdiary.di

import com.google.gson.Gson
import org.koin.dsl.module

val utilityModule = module {

    single { Gson() }

}