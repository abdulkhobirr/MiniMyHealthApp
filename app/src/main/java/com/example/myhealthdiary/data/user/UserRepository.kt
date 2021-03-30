package com.example.myhealthdiary.data.user

import com.example.myhealthdiary.data.user.entity.User

interface UserRepository{
    fun getUser(username: String, password: String): List<User>

    fun getUserData(username: String): User

    suspend fun addUser(user: User)
}