package com.semdelion.data.storages.models

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(val firstName: String, val lastName: String) {
}