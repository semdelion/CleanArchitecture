package com.semdelion.domain.usecases

import com.semdelion.domain.models.UserModel
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

class SaveUserUseCaseTestModel {
    private val userRepository = mock<IUserRepository>()

    @AfterEach
    fun clearData() {
        Mockito.reset(userRepository)
    }

    companion object {
        @JvmStatic
        fun users() = listOf(
            Arguments.of(UserModel("", ""), false),
            Arguments.of(UserModel("first", ""), false),
            Arguments.of(UserModel("", "last"), false),
            Arguments.of(UserModel("first", "last"), true)
        )
    }

    @ParameterizedTest(name = "try save user {0} should equal {1}")
    @MethodSource("users")
    fun trySaveEmptyUser(userModel: UserModel, expected: Boolean) {

        Mockito.`when`(userRepository.getUser()).thenReturn(userModel)

        val saveUserUseCase = SaveUserUseCase(repository = userRepository)
        val actual = saveUserUseCase.execute(userModel)

        assertEquals(expected, actual)
        Mockito.verify(userRepository, Mockito.never()).saveUser(any())
    }

    @Test
    fun userAlreadySaved() {
        val testUserModel = UserModel(firstName = "firstName", lastName = "lastName")
        Mockito.`when`(userRepository.getUser()).thenReturn(testUserModel)

        val saveUserUseCase = SaveUserUseCase(repository = userRepository)

        val actual =
            saveUserUseCase.execute(userModel = UserModel(firstName = "firstName", lastName = "lastName"))
        assertEquals(true, actual)
        Mockito.verify(userRepository, Mockito.never()).saveUser(any())
    }

    @Test
    fun successSaveUser() {
        val testUserModel = UserModel(firstName = "firstNameOld", lastName = "lastName")
        Mockito.`when`(userRepository.getUser()).thenReturn(testUserModel)

        val expected = true
        val currentUserModel = UserModel(firstName = "newFirstName", lastName = "newLastName")
        Mockito.`when`(userRepository.saveUser(currentUserModel)).thenReturn(expected)

        val saveUserUseCase = SaveUserUseCase(repository = userRepository)

        val actual = saveUserUseCase.execute(userModel = currentUserModel)
        assertEquals(expected, actual)
        Mockito.verify(userRepository, Mockito.times(1)).saveUser(userModel = currentUserModel)
    }

    @Test
    fun notSuccessSaveUser() {
        val testUserModel = UserModel(firstName = "firstNameOld", lastName = "lastName")
        Mockito.`when`(userRepository.getUser()).thenReturn(testUserModel)

        val expected = false
        val currentUserModel = UserModel(firstName = "newFirstName", lastName = "newLastName")
        Mockito.`when`(userRepository.saveUser(currentUserModel)).thenReturn(expected)

        val saveUserUseCase = SaveUserUseCase(repository = userRepository)

        val actual = saveUserUseCase.execute(userModel = currentUserModel)
        assertEquals(expected, actual)
        Mockito.verify(userRepository, Mockito.times(1)).saveUser(userModel = currentUserModel)
    }
}