package com.example.myhealthdiary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhealthdiary.data.user.UserRepository
import com.example.myhealthdiary.data.user.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
        private val repository: UserRepository,
        application: Application): AndroidViewModel(application){

    var user: List<User> ?= null

    var userData: User ?= null

    fun addUser(user: User) = viewModelScope.launch(Dispatchers.IO){
        repository.addUser(user)
    }

    fun getUser(username: String, password: String) = viewModelScope.launch(Dispatchers.IO){
        user = repository.getUser(username, password)
    }

    fun getUserData(username: String)  = viewModelScope.launch(Dispatchers.IO){
        userData = repository.getUserData(username)
    }
}