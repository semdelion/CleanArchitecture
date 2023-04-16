package com.semdelion.data.storages

import com.semdelion.data.storages.models.UserModel

interface IUserStorage {
    fun save(user: UserModel): Boolean
    fun get(): UserModel
}