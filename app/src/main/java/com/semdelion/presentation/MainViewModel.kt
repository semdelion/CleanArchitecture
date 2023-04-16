package com.semdelion.presentation

import androidx.lifecycle.ViewModel
import com.semdelion.domain.repositories.IUserRepository
import com.semdelion.domain.usecases.GetUser
import com.semdelion.domain.usecases.SaveUser

class MainViewModel(repository: IUserRepository) : ViewModel() {
    private val userRepository: IUserRepository = repository

    val getUser = GetUser(userRepository)
    val saveUser = SaveUser(userRepository)
}