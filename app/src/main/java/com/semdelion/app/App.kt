package com.semdelion.app

import android.app.Application
import com.semdelion.data.storages.Storages

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Storages.init(applicationContext)
    }
}