package com.semdelion.domain.usecases

import com.semdelion.domain.models.UserModel
import com.semdelion.domain.repositories.IUserRepository

class SaveUserUseCase(private val repository: IUserRepository) {
    fun execute(userModel: UserModel): Boolean {

        if (userModel.firstName.isEmpty() || userModel.lastName.isEmpty())
            return false

        val oldUser = repository.getUser()

        if (oldUser == userModel)
            return true

        return repository.saveUser(userModel)
    }
}