package com.example.ditest.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ditest.database.entities.User

@Dao
interface UserDao {

    @Query("select * from User")
    suspend fun getUsers(): List<User>

    @Insert
    suspend fun insertUser(user: User)

}