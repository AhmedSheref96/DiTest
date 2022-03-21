package com.example.ditest.ui.home.home_activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.ditest.R
import com.example.ditest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHost =
            supportFragmentManager.findFragmentById(binding.navHost.id) as NavHostFragment

        val navController = navHost.navController

        binding.topAppBar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }

        binding.drawerLayout.apply {
            AppBarConfiguration(setOf(R.id.cart_nav, R.id.home_nav), binding.drawerLayout)
        }

        binding.navView.setupWithNavController(navController)
/*
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.electronics -> {
                    binding.drawerLayout.close()
                    Toast.makeText(this, "electronics", Toast.LENGTH_LONG).show()
                }
                R.id.menClothing -> {

                }
                R.id.womenClothing -> {

                }
                R.id.jewelery -> {

                }
            }
            return@setNavigationItemSelectedListener true
        }*/

    }

}