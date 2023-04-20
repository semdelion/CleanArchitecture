package com.semdelion.di

import com.semdelion.data.repositories.UserRepository
import com.semdelion.data.storages.IUserStorage
import com.semdelion.data.storages.SharedPrefUserStorage
import com.semdelion.domain.repositories.IUserRepository
import org.koin.dsl.module

val dataModule = module {
    single<IUserRepository>{
        UserRepository(userStorage = get())}

    single<IUserStorage>{
        SharedPrefUserStorage(context = get())
    }
}