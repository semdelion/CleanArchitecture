package com.semdelion.di

import com.semdelion.domain.usecases.GetUser
import com.semdelion.domain.usecases.SaveUser
import org.koin.dsl.module

val domainModule = module {
    factory<GetUser> {
        GetUser(repository = get())
    }

    factory<SaveUser> {
        SaveUser(repository = get())
    }
}