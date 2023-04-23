package com.semdelion.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semdelion.domain.models.User
import com.semdelion.domain.usecases.GetUser
import com.semdelion.domain.usecases.SaveUser
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class MainViewModel(private val getUser: GetUser, private val saveUser: SaveUser) : ViewModel() {

    private val _loadedUserLive = MutableLiveData<String>("")
    val loadedUserLive: LiveData<String> = _loadedUserLive

    private val _errorFirstNameLive = MutableLiveData<String>("")
    val errorFirstNameLive:LiveData<String> = _errorFirstNameLive

    private val _errorLastNameLive = MutableLiveData<String>("")
    val errorLastNameLive:LiveData<String> = _errorLastNameLive

    val firstNameLive = MutableLiveData<String>("")
    val lastNameLive = MutableLiveData<String>("")

    fun save() {
        _errorFirstNameLive.value = if (firstNameLive.value.isNullOrBlank()) "First name is empty" else ""
        _errorLastNameLive.value = if(lastNameLive.value.isNullOrBlank()) "Last name is empty" else ""

        val result = saveUser.execute(User(firstName = firstNameLive.value ?: "", lastName = lastNameLive.value ?: ""))
    }

    fun load() {
        val user = getUser.execute()
        _loadedUserLive.value = "${user.firstName}, ${user.lastName}"
    }
}