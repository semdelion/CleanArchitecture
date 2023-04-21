package com.semdelion.di

import com.semdelion.presentation.MainFragment
import dagger.Component
import dagger.Module

@Component(modules = [AppModule::class, DataModule::class, DomainModule::class])
interface IAppComponent {
    fun inject(mainFragment: MainFragment)
}