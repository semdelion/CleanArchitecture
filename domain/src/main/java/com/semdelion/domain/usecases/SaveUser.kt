package com.semdelion.domain.usecases

import com.semdelion.domain.models.User
import com.semdelion.domain.repositories.IUserRepository

class SaveUser(private val repository: IUserRepository) {
    fun execute(user: User): Boolean {

        if (user.firstName.isEmpty() || user.lastName.isEmpty())
            return false

        val oldUser = repository.getUser()

        if (oldUser == user)
            return true

        return repository.saveUser(user)
    }
}