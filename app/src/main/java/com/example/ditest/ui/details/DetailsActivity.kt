package com.example.ditest.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ditest.R
import com.example.network.products.ProductsClient
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailst)

    }
}