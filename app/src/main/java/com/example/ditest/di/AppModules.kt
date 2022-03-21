package com.example.ditest.di

import android.content.Context
import androidx.room.Room
import com.example.ditest.database.DataBase
import com.example.ditest.database.daos.UserDao
import com.example.network.products.ProductsService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
}


@Module
@InstallIn(SingletonComponent::class)
object activityModule {

    @Provides
    @Singleton
    fun getRetrofitInstance(moshi: Moshi): Retrofit {
        Timber.d("-------------- Retrofit Instance Created")
        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    /*
    @Provides
    @ActivityScoped
    fun getProductsClient(service: ProductsService): ProductsClient =
        ProductsClient(service)

    */


}

@Module
@InstallIn(ViewModelComponent::class)
object repositoriesModule {

    @Provides
    @ViewModelScoped
    fun getProductService(retrofit: Retrofit): ProductsService =
        retrofit.create(ProductsService::class.java)

//    @Provides
//    @ViewScoped
//    fun getProductsRepository(client: ProductsClient) = ProductsRepository()

}

