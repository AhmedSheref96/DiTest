package com.example.ditest.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ditest.R
import com.example.ditest.database.daos.UserDao
import com.example.ditest.database.entities.User
import com.example.ditest.ui.products.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    @Named("helloString")
    lateinit var holloStr: String
    private val viewModel: ProductsViewModel by viewModels()

    @Inject
    lateinit var dao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.d("---------- $holloStr")

        val user = User(0, "ahmed sheref", "ahmedel3asas123@gmail.com")

        GlobalScope.launch {
            dao.insertUser(user)
        }

    }

}