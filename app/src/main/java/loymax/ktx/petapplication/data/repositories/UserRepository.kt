package loymax.ktx.petapplication.data.repositories

import android.content.Context
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import loymax.ktx.petapplication.domain.models.User
import loymax.ktx.petapplication.domain.repositories.IUserRepository

private const val SHARED_NAME_USER_DB = "shared_preferences_bd"
private const val USER_TEST = "user_test"

class UserRepository(private val context: Context): IUserRepository {

    private val sharedPreferences = context.getSharedPreferences(SHARED_NAME_USER_DB, Context.MODE_PRIVATE)

    override fun saveUser(user: User): Boolean {
        val json = Json.encodeToString(user)
        sharedPreferences.edit().putString(USER_TEST, json).apply()
        return true
    }

    override fun getUser(): User {
        val jsonUser = sharedPreferences.getString(USER_TEST, "")
        var user = User("","")
        jsonUser?.let {
            try {
                user = Json.decodeFromString<User>(it)
            }
            catch (ex: Exception) {
                //TODO add logs
            }
        }
        return user
    }
}