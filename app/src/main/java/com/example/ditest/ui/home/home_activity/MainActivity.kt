package com.example.ditest.ui.home.home_activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.ditest.NavGraphDirections
import com.example.ditest.R
import com.example.ditest.databinding.ActivityMainBinding
import com.example.ditest.utils.navigate
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

        navController.addOnDestinationChangedListener { _: NavController, des: NavDestination, _ ->
            when (des.id) {
                R.id.home_nav -> {
                    binding.title.text = "home"
                }
                R.id.cart_nav -> {
                    binding.title.text = "cart"
                }
                R.id.womenClothing -> {
                    binding.title.text = "women clothing"
                }
                R.id.menClothing -> {
                    binding.title.text = "men clothing"
                }
                R.id.electronics -> {
                    binding.title.text = "electronics"
                }
                R.id.jewelery -> {
                    binding.title.text = "jewelery"
                }
            }
        }

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home_nav -> {
                    binding.drawerLayout.close()
                    navigate(navController, NavGraphDirections.openHome())
                }
                R.id.cart_nav -> {
                    binding.drawerLayout.close()
                    navigate(navController, NavGraphDirections.openCart())
                }
                R.id.electronics -> {
                    binding.drawerLayout.close()
                    Toast.makeText(this, "electronics", Toast.LENGTH_LONG).show()
                }
                R.id.menClothing -> {

                    binding.drawerLayout.close()
                }
                R.id.womenClothing -> {

                    binding.drawerLayout.close()
                }
                R.id.jewelery -> {

                    binding.drawerLayout.close()
                }
            }
            return@setNavigationItemSelectedListener true
        }

    }

}