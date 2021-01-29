package com.example.myhealthdiary.utils

import android.app.Application
import android.content.Context
import com.example.myhealthdiary.di.apiModule
import com.example.myhealthdiary.di.chatModule
import com.example.myhealthdiary.di.rxModule
import com.example.myhealthdiary.di.utilityModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class BaseApplication : Application() {
    companion object {
        var context: Context? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()

        context = this

        startKoin {
            androidContext(this@BaseApplication)
            modules(getDefinedModules())
        }
    }

    private fun getDefinedModules(): List<Module> {
        return listOf(
            apiModule,
            rxModule,
            utilityModule,
            chatModule
        )
    }
}