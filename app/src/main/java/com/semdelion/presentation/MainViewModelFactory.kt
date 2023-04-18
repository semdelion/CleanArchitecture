/*package com.semdelion.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.semdelion.data.repositories.UserRepository
import com.semdelion.data.storages.SharedPrefUserStorage
import com.semdelion.domain.usecases.GetUser
import com.semdelion.domain.usecases.SaveUser

class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val repository by lazy {
        UserRepository(userStorage = SharedPrefUserStorage(context = context))
    }

    private val getUserUseCase by lazy { GetUser(repository = repository) }

    private val saveUserUseCase by lazy { SaveUser(repository = repository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getUserUseCase, saveUserUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/
