package com.semdelion.app

import android.app.Application
import com.semdelion.di.appModule
import com.semdelion.di.dataModule
import com.semdelion.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        initializationKoin()
    }

    private fun initializationKoin() {
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@App)
            modules(listOf(
                appModule,
                dataModule,
                domainModule
            ))
        }
    }
}