package com.semdelion.domain.repositories

import com.semdelion.domain.models.UserModel

interface IUserRepository {
    fun saveUser(userModel: UserModel): Boolean
    fun getUser(): UserModel
}