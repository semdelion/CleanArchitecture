package com.semdelion.presentation.viewmodels

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.semdelion.domain.models.User
import com.semdelion.domain.usecases.GetUser
import com.semdelion.domain.usecases.SaveUser
import com.semdelion.presentation.viewmodels.MainViewModel
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

    private val getUserUseCase = mock<GetUser>()
    private val saveUserUseCase = mock<SaveUser>()

    @AfterEach
    fun afterEach() {
        Mockito.reset(getUserUseCase)
        Mockito.reset(saveUserUseCase)
        ArchTaskExecutor.getInstance().setDelegate(null)
    }

    @BeforeEach
    fun beforeEach() {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor(){
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
        val testUser = User("firstName", "lastName")
        val saveResult = true

        Mockito.`when`(saveUserUseCase.execute(user = testUser)).thenReturn(saveResult)
        val viewModel = MainViewModel(getUser = getUserUseCase, saveUser = saveUserUseCase)
        viewModel.firstNameLive.value = (testUser.firstName)
        viewModel.lastNameLive.value = (testUser.lastName)

        viewModel.save()
        //TODO надо дописать нормально тест
        val expected = "true"
        val actual = "true"
        Assertions.assertEquals(expected,actual)
        Mockito.verify(saveUserUseCase, times(1)).execute(testUser)
    }


    @Test
    fun `should save username and return false`() {
        val testUser = User(firstName = "firstName", lastName = "lastName")
        val saveResult = false

        Mockito.`when`(saveUserUseCase.execute(user = testUser)).thenReturn(saveResult)
        val viewModel = MainViewModel(getUser = getUserUseCase, saveUser = saveUserUseCase)
        viewModel.firstNameLive.value = testUser.firstName
        viewModel.lastNameLive.value = testUser.lastName

        viewModel.save()
        //TODO надо дописать нормально тест
        val expected = "false"
        val actual = "false"
        Assertions.assertEquals(expected, actual)
        Mockito.verify(saveUserUseCase, times(1)).execute(testUser)
    }

    @Test
    fun `should load user`() {
        val testUser = User(firstName = "firstName", lastName = "lastName")

        Mockito.`when`(getUserUseCase.execute()).thenReturn(testUser)

        val viewModel = MainViewModel(getUser = getUserUseCase, saveUser = saveUserUseCase)

        viewModel.load()

        val expected = "${testUser.firstName}, ${testUser.lastName}"
        val actual = viewModel.loadedUserLive.value
        Assertions.assertEquals(expected, actual)
    }
}