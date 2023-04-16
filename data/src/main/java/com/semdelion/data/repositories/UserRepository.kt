package com.semdelion.data.repositories

import com.semdelion.data.storages.IUserStorage
import com.semdelion.data.storages.models.UserModel
import com.semdelion.domain.models.User
import com.semdelion.domain.repositories.IUserRepository

class UserRepository(private val userStorage: IUserStorage): IUserRepository {

    override fun saveUser(user: User): Boolean {
        val userModel = UserModel(firstName = user.firstName, lastName = user.lastName)
        return userStorage.save(userModel)
    }

    override fun getUser(): User {
        val userModel = userStorage.get()
        return User(firstName = userModel.firstName, lastName = userModel.lastName)
    }
}