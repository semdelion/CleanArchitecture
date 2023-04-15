package loymax.ktx.petapplication.ui.main

import androidx.lifecycle.ViewModel
import loymax.ktx.petapplication.data.repositories.UserRepository
import loymax.ktx.petapplication.domain.repositories.IUserRepository
import loymax.ktx.petapplication.domain.usecases.GetUser
import loymax.ktx.petapplication.domain.usecases.SaveUser

class MainViewModel(repository: IUserRepository) : ViewModel() {
    private val userRepository: IUserRepository = repository

    val getUser = GetUser(userRepository)
    val saveUser = SaveUser(userRepository)
}