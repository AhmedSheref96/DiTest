package com.example.ditest.di

import android.content.Context
import androidx.room.Room
import com.example.ditest.database.DataBase
import com.example.ditest.database.daos.UserDao
import com.example.network.EndPoint
import com.example.network.products.ProductsService
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
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
}


@Module
@InstallIn(SingletonComponent::class)
object activityModule {

    @Provides
    @Singleton
    fun getRetrofitInstance(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        Timber.d("-------------- Retrofit Instance Created")
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(EndPoint.baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun getOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                LoggingInterceptor.Builder()
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .request(" requeeeeeeeest   ")
                    .response(" responsssse   ")
                    .build()
            )
            .readTimeout(EndPoint.connectionTimeOut, TimeUnit.MILLISECONDS)
            .writeTimeout(EndPoint.connectionTimeOut, TimeUnit.MILLISECONDS)
            .connectTimeout(EndPoint.connectionTimeOut, TimeUnit.MILLISECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}

@Module
@InstallIn(ViewModelComponent::class)
object repositoriesModule {

    @Provides
    @ViewModelScoped
    fun getProductService(retrofit: Retrofit): ProductsService =
        retrofit.create(ProductsService::class.java)


}

