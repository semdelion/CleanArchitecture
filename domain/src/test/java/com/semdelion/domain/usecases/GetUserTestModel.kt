package com.semdelion.domain.usecases

import com.semdelion.domain.models.UserModel
import com.semdelion.domain.repositories.IUserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetUserTestModel {
    private val userRepository = mock<IUserRepository>()

    @Test
    fun shouldReturnCorrectUser() {

        val testUserModel = UserModel(firstName = "firstName", lastName = "lastName")
        Mockito.`when`(userRepository.getUser()).thenReturn(testUserModel)

        val getUserUseCase = GetUserUseCase(repository = userRepository)
        val actual = getUserUseCase.execute()
        val expected = UserModel(firstName = "firstName", lastName = "lastName")

        Assertions.assertEquals(expected, actual)
    }
}