package com.semdelion.domain.repositories

import com.semdelion.domain.models.User

interface IUserRepository {
    fun saveUser(user: User): Boolean
    fun getUser(): User
}