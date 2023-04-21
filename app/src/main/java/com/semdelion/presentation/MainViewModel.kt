package com.semdelion.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semdelion.domain.models.User
import com.semdelion.domain.usecases.GetUser
import com.semdelion.domain.usecases.SaveUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getUser: GetUser, private val saveUser: SaveUser) : ViewModel() {

    private val _loadedUserLive = MutableLiveData<String>("")
    val loadedUserLive:LiveData<String> = _loadedUserLive

    val firstNameLive = MutableLiveData<String>("")
    val lastNameLive = MutableLiveData<String>("")

    fun save() {
       val result = saveUser.execute(User(firstName = firstNameLive.value ?: "", lastName = lastNameLive.value ?: ""))
    }

    fun load() {
        val user = getUser.execute()
        _loadedUserLive.value = "${user.firstName}, ${user.lastName}"
    }
}