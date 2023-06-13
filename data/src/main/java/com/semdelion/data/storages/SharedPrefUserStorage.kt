package com.semdelion.data.storages

import com.semdelion.data.storages.models.UserDataModel
import android.content.Context
import com.semdelion.data.storages.interfaces.IUserStorage
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private const val SHARED_NAME_USER_DB = "shared_preferences_bd"
private const val USER_TEST = "user_test"

class SharedPrefUserStorage(context: Context) : IUserStorage {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_NAME_USER_DB, Context.MODE_PRIVATE)

    override fun save(user: UserDataModel): Boolean {
        try {
            val json = Json.encodeToString(user)
            sharedPreferences.edit().putString(USER_TEST, json).apply()
        } catch (ex: Exception) {
            return false
            //TODO add error logs
        }

        return true
    }

    override fun get(): UserDataModel {
        val jsonUser = sharedPreferences.getString(USER_TEST, "")
        var user = UserDataModel("", "")
        jsonUser?.let {
            try {
                user = Json.decodeFromString(it)
            } catch (ex: Exception) {
                //TODO add error logs
            }
        }
        return user
    }
}