package com.semdelion.domain.usecases

import com.semdelion.domain.models.User
import com.semdelion.domain.repositories.IUserRepository

class GetUser(private val repository: IUserRepository) {
    fun execute(): User {
        return repository.getUser()
    }
}