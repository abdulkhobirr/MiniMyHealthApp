package com.example.myhealthdiary.data.user.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myhealthdiary.data.user.entity.User


@Dao
interface UserDao {
    @Query ("SELECT * FROM user_table WHERE Username= :username AND Password= :password")
    fun getUser(username:String, password: String): List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)
}
