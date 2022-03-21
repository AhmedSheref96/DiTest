package com.example.network.products

import android.util.Log
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class ProductsClient @Inject constructor(private val service: ProductsService) {

    suspend fun getAllProducts() = withContext(Dispatchers.IO) {
        service.getAllProducts()
    }

    suspend fun getSpecificProduct(id: Int) = withContext(Dispatchers.IO) {
        service.getSpecificProduct(id)
    }

    suspend fun getLimitedProducts(limit: Int) = withContext(Dispatchers.IO) {
        service.getLimitedProducts(limit)
    }

    suspend fun getProductsCategory() = withContext(Dispatchers.IO) {
        service.getProductsCategory()
    }

}