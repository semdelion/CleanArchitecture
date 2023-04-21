package com.semdelion.app

import android.app.Application
import com.semdelion.di.AppModule
import com.semdelion.di.DaggerIAppComponent
import com.semdelion.di.IAppComponent

class App: Application() {

    lateinit var appComponent: IAppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerIAppComponent
            .builder()
            .appModule(AppModule(applicationContext))
            .build()
    }
}