package com.semdelion.data.repositories

import com.semdelion.data.storages.interfaces.IUserStorage
import com.semdelion.data.storages.models.UserDataModel
import com.semdelion.data.storages.models.toUserModel
import com.semdelion.domain.models.UserModel
import com.semdelion.domain.repositories.IUserRepository

class UserRepositoryImpl(private val userStorage: IUserStorage) : IUserRepository {

    override fun saveUser(userModel: UserModel): Boolean {
        val userDataModel = UserDataModel(
            firstName = userModel.firstName,
            lastName = userModel.lastName
        )
        return userStorage.save(userDataModel)
    }

    override fun getUser(): UserModel {
        val userDataModel = userStorage.get()
        return userDataModel.toUserModel()
    }
}