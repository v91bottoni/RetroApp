package com.v1bottoni.retroapp

import android.app.Application
import com.v1bottoni.retroapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module


class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(mutableListOf<Module>().apply {
                add(appCoreModule)
                add(mapperModule)
                add(repositoriesModule)
                add(useCasesModule)
                add(viewModelModule)
            })
        }
    }
}