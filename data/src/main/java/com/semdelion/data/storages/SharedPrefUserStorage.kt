package com.semdelion.data.storages

import com.semdelion.data.storages.models.UserModel
import android.content.Context
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private const val SHARED_NAME_USER_DB = "shared_preferences_bd"
private const val USER_TEST = "user_test"

class SharedPrefUserStorage(private val context: Context): IUserStorage {

    private val sharedPreferences = context.getSharedPreferences(SHARED_NAME_USER_DB, Context.MODE_PRIVATE)

    override fun save(user: UserModel): Boolean {
        try {
            val json = Json.encodeToString(user)
            sharedPreferences.edit().putString(USER_TEST, json).apply()
        }
        catch (ex: Exception) {
            return false
            //TODO add error logs
        }

        return true
    }

    override fun get(): UserModel {
        val jsonUser = sharedPreferences.getString(USER_TEST, "")
        var user = UserModel("","")
        jsonUser?.let {
            try {
                user = Json.decodeFromString<UserModel>(it)
            }
            catch (ex: Exception) {
                //TODO add error logs
            }
        }
        return user
    }
}