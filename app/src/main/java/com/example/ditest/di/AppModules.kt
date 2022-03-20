package com.example.ditest.di

import android.content.Context
import androidx.room.Room
import com.example.ditest.database.DataBase
import com.example.ditest.database.daos.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Named
import javax.inject.Singleton


//SingletonComponent
//ViewModelComponent
//ActivityComponent

@Module
@InstallIn(SingletonComponent::class)
object appModules {

    @Provides
    @Singleton
    @Named("helloString")
    fun getHelloString() = "I'm getting hello World"

    @Provides
    @Singleton
    fun getDataBase(@ApplicationContext context: Context): DataBase {
        Timber.d("---------------- dataBase Created By DI")
        return Room.databaseBuilder(context, DataBase::class.java, "dataBase")
            .build()
    }

    @Provides
    @Singleton
    fun getUserDao(dataBase: DataBase): UserDao {
        return dataBase.dao()
    }

    @Provides
    @Singleton
    fun getRetrofitInstance(): Retrofit {
        Retrofit.Builder()
            .baseUrl("")
            .build()
    }

}

