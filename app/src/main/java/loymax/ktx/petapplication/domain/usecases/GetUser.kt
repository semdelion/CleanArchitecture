package loymax.ktx.petapplication.domain.usecases

import loymax.ktx.petapplication.domain.models.User
import loymax.ktx.petapplication.domain.repositories.IUserRepository

class GetUser(private val repository: IUserRepository) {
    fun execute(): User {
        return repository.getUser()
    }
}