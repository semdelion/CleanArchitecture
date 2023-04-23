package com.semdelion.domain.usecases

import com.semdelion.domain.models.User
import com.semdelion.domain.repositories.IUserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetUserTest {
    private val userRepository = mock<IUserRepository>()

    @Test
    fun shouldReturnCorrectUser() {

        val testUser = User(firstName = "firstName", lastName = "lastName")
        Mockito.`when`(userRepository.getUser()).thenReturn(testUser)

        val getUserUseCase = GetUser(repository = userRepository)
        val actual = getUserUseCase.execute()
        val expected = User(firstName = "firstName", lastName = "lastName")

        Assertions.assertEquals(expected, actual)
    }
}