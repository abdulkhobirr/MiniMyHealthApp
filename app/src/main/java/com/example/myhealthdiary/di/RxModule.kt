package com.example.myhealthdiary.di

import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.module

val rxModule = module {

    factory { CompositeDisposable() }

}