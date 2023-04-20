package com.semdelion.di

import com.semdelion.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{
    viewModel<MainViewModel>{
        MainViewModel(
            getUser = get(),
            saveUser = get()
        )
    }
}