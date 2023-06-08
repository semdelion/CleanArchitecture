package com.semdelion.data.storages.models

import com.semdelion.domain.models.UserModel
import kotlinx.serialization.Serializable

@Serializable
data class UserDataModel(val firstName: String, val lastName: String) {
}

fun UserDataModel.toUserModel(): UserModel {
    return UserModel(firstName = firstName, lastName = lastName)
}