package com.semdelion.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.semdelion.domain.usecases.GetUser
import com.semdelion.domain.usecases.SaveUser

class MainViewModelFactory(private val getUserUseCase: GetUser, private val saveUserUseCase: SaveUser)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                getUser =  getUserUseCase,
                saveUser = saveUserUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
