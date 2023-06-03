package com.semdelion.domain.usecases

import com.semdelion.domain.models.User
import com.semdelion.domain.repositories.IUserRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

class SaveUserTest {
    private val userRepository = mock<IUserRepository>()

    @AfterEach
    fun clearData() {
        Mockito.reset(userRepository)
    }

    companion object {
        @JvmStatic
        fun users() = listOf(
            Arguments.of(User("", ""), false),
            Arguments.of(User("first", ""), false),
            Arguments.of(User("", "last"), false),
            Arguments.of(User("first", "last"), true)
        )
    }

    @ParameterizedTest(name = "try save user {0} should equal {1}")
    @MethodSource("users")
    fun trySaveEmptyUser(user: User, expected: Boolean) {

        Mockito.`when`(userRepository.getUser()).thenReturn(user)

        val saveUserUseCase = SaveUser(repository = userRepository)
        val actual = saveUserUseCase.execute(user)

        assertEquals(expected, actual)
        Mockito.verify(userRepository, Mockito.never()).saveUser(any())
    }

    @Test
    fun userAlreadySaved() {
        val testUser = User(firstName = "firstName", lastName = "lastName")
        Mockito.`when`(userRepository.getUser()).thenReturn(testUser)

        val saveUserUseCase = SaveUser(repository = userRepository)

        val actual =
            saveUserUseCase.execute(user = User(firstName = "firstName", lastName = "lastName"))
        assertEquals(true, actual)
        Mockito.verify(userRepository, Mockito.never()).saveUser(any())
    }

    @Test
    fun successSaveUser() {
        val testUser = User(firstName = "firstNameOld", lastName = "lastName")
        Mockito.`when`(userRepository.getUser()).thenReturn(testUser)

        val expected = true
        val currentUser = User(firstName = "newFirstName", lastName = "newLastName")
        Mockito.`when`(userRepository.saveUser(currentUser)).thenReturn(expected)

        val saveUserUseCase = SaveUser(repository = userRepository)

        val actual = saveUserUseCase.execute(user = currentUser)
        assertEquals(expected, actual)
        Mockito.verify(userRepository, Mockito.times(1)).saveUser(user = currentUser)
    }

    @Test
    fun notSuccessSaveUser() {
        val testUser = User(firstName = "firstNameOld", lastName = "lastName")
        Mockito.`when`(userRepository.getUser()).thenReturn(testUser)

        val expected = false
        val currentUser = User(firstName = "newFirstName", lastName = "newLastName")
        Mockito.`when`(userRepository.saveUser(currentUser)).thenReturn(expected)

        val saveUserUseCase = SaveUser(repository = userRepository)

        val actual = saveUserUseCase.execute(user = currentUser)
        assertEquals(expected, actual)
        Mockito.verify(userRepository, Mockito.times(1)).saveUser(user = currentUser)
    }
}