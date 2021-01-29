package com.example.myhealthdiary.data.user

import androidx.lifecycle.LiveData
import com.example.myhealthdiary.data.user.dao.UserDao
import com.example.myhealthdiary.data.user.entity.User

class UserRepository(private val userDao: UserDao){
    fun getUser(username: String, password: String): List<User> {
        return userDao.getUser(username, password)
    }

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}