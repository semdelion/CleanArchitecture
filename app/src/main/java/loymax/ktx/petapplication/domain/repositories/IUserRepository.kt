package loymax.ktx.petapplication.domain.repositories

import loymax.ktx.petapplication.domain.models.User

interface IUserRepository {
    fun saveUser(user: User): Boolean
    fun getUser(): User
}