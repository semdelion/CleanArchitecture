package com.semdelion.presentation.viewmodels

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.semdelion.domain.models.UserModel
import com.semdelion.domain.usecases.GetUserUseCase
import com.semdelion.domain.usecases.SaveUserUseCase
import org.junit.Test

import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class MainViewModelTest {

    private val getUserUseCase = mock<GetUserUseCase>()
    private val saveUserUseCase = mock<SaveUserUseCase>()

    @AfterEach
    fun afterEach() {
        Mockito.reset(getUserUseCase)
        Mockito.reset(saveUserUseCase)
        ArchTaskExecutor.getInstance().setDelegate(null)
    }

    @BeforeEach
    fun beforeEach() {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }
        })
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `should save username and return true`() {
        val testUserModel = UserModel("firstName", "lastName")
        val saveResult = true

        Mockito.`when`(saveUserUseCase.execute(userModel = testUserModel)).thenReturn(saveResult)
        val viewModel = MainViewModel(getUser = getUserUseCase, saveUser = saveUserUseCase)
        viewModel.firstNameLive.value = (testUserModel.firstName)
        viewModel.lastNameLive.value = (testUserModel.lastName)

        viewModel.save()
        //TODO надо дописать нормально тест
        val expected = "true"
        val actual = "true"
        Assertions.assertEquals(expected, actual)
        Mockito.verify(saveUserUseCase, times(1)).execute(testUserModel)
    }


    @Test
    fun `should save username and return false`() {
        val testUserModel = UserModel(firstName = "firstName", lastName = "lastName")
        val saveResult = false

        Mockito.`when`(saveUserUseCase.execute(userModel = testUserModel)).thenReturn(saveResult)
        val viewModel = MainViewModel(getUser = getUserUseCase, saveUser = saveUserUseCase)
        viewModel.firstNameLive.value = testUserModel.firstName
        viewModel.lastNameLive.value = testUserModel.lastName

        viewModel.save()
        //TODO надо дописать нормально тест
        val expected = "false"
        val actual = "false"
        Assertions.assertEquals(expected, actual)
        Mockito.verify(saveUserUseCase, times(1)).execute(testUserModel)
    }

    @Test
    fun `should load user`() {
        val testUserModel = UserModel(firstName = "firstName", lastName = "lastName")

        Mockito.`when`(getUserUseCase.execute()).thenReturn(testUserModel)

        val viewModel = MainViewModel(getUser = getUserUseCase, saveUser = saveUserUseCase)

        viewModel.load()

        val expected = "${testUserModel.firstName}, ${testUserModel.lastName}"
        val actual = viewModel.loadedUserLive.value
        Assertions.assertEquals(expected, actual)
    }
}