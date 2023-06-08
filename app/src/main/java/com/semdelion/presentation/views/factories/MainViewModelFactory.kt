package com.semdelion.presentation.views.factories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.semdelion.data.repositories.UserRepositoryImpl
import com.semdelion.data.storages.SharedPrefUserStorage
import com.semdelion.domain.usecases.GetUserUseCase
import com.semdelion.domain.usecases.SaveUserUseCase
import com.semdelion.presentation.viewmodels.MainViewModel

class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val repository by lazy {
        UserRepositoryImpl(userStorage = SharedPrefUserStorage(context = context))
    }

    private val getUserUseCase by lazy { GetUserUseCase(repository = repository) }

    private val saveUserUseCase by lazy { SaveUserUseCase(repository = repository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getUserUseCase, saveUserUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
