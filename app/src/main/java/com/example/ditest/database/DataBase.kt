package com.example.ditest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ditest.database.daos.UserDao
import com.example.ditest.database.entities.User


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun dao(): UserDao

}