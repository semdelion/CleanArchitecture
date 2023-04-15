package loymax.ktx.petapplication.domain.usecases

import loymax.ktx.petapplication.domain.models.User
import loymax.ktx.petapplication.domain.repositories.IUserRepository

class SaveUser(private val repository: IUserRepository) {
    fun execute(user: User): Boolean {
        return repository.saveUser(user)
    }
}