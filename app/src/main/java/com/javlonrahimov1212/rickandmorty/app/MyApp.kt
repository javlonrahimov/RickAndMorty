package com.javlonrahimov1212.rickandmorty.app

import android.app.Application
import com.javlonrahimov1212.rickandmorty.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            koin.loadModules(listOf(appModule))
        }
    }
}