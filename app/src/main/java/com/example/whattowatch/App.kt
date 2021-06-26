package com.example.whattowatch

import android.app.Application
import com.example.whattowatch.di.networkModule
import com.example.whattowatch.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)

            modules(viewModelsModule, networkModule)
        }
    }
}