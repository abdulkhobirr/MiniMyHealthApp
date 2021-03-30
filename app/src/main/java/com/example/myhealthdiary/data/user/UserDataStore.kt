package com.example.myhealthdiary.data.user

import com.example.myhealthdiary.data.user.dao.UserDao
import com.example.myhealthdiary.data.user.entity.User

class UserDataStore (local: UserDao): UserRepository {
    private val localDb = local

    override fun getUser(username: String, password: String): List<User> {
        return localDb.getUser(username, password)
    }

    override fun getUserData(username: String): User {
        return localDb.getUserData(username)
    }

    override suspend fun addUser(user: User){
        localDb.addUser(user)
    }
}