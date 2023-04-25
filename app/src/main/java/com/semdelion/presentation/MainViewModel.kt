package com.semdelion.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semdelion.domain.models.User
import com.semdelion.domain.usecases.GetUser
import com.semdelion.domain.usecases.SaveUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(private val getUser: GetUser, private val saveUser: SaveUser) : ViewModel() {

    private val _loadedUserLive = MutableLiveData<String>("")
    val loadedUserLive: LiveData<String> = _loadedUserLive

    private val _errorFirstNameLive = MutableLiveData<String>("")
    val errorFirstNameLive:LiveData<String> = _errorFirstNameLive

    private val _errorLastNameLive = MutableLiveData<String>("")
    val errorLastNameLive:LiveData<String> = _errorLastNameLive

    val firstNameLive = MutableLiveData<String>("")
    val lastNameLive = MutableLiveData<String>("")

    private val _useCaseState = MutableSharedFlow<String>()
    val useCaseState = _useCaseState.asSharedFlow()

    fun save() {
        viewModelScope.launch {
            _errorFirstNameLive.value =
                if (firstNameLive.value.isNullOrBlank()) "First name is empty" else ""
            _errorLastNameLive.value =
                if (lastNameLive.value.isNullOrBlank()) "Last name is empty" else ""

            val result = saveUser.execute(
                User(
                    firstName = firstNameLive.value ?: "",
                    lastName = lastNameLive.value ?: ""
                )
            )
            _useCaseState.emit(if(result) "Successful save!" else "Failure save!")
        }

    }

    fun load() {
        viewModelScope.launch {
            val user = getUser.execute()
            _loadedUserLive.value = "${user.firstName}, ${user.lastName}"
            if(user.firstName.isEmpty() and user.lastName.isEmpty())
                _useCaseState.emit("DB is empty")
        }
    }
}