package com.semdelion.data.storages.interfaces

import com.semdelion.data.storages.models.UserDataModel

interface IUserStorage {
    fun save(user: UserDataModel): Boolean
    fun get(): UserDataModel
}