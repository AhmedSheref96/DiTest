package com.example.ditest.ui.home.products

import com.example.models.products.ResponseItem
import com.example.network.products.ProductsClient
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ProductsRepository @Inject constructor(private val client: ProductsClient) {

    suspend fun getProductsCategories(
        onLoading: (Boolean) -> Unit,
        onError: (String) -> Unit,
        onEmpty: (Boolean) -> Unit,
        onSuccess: (Array<String>) -> Unit
    ) {
        onLoading(true)
        try {
            val response = client.getProductsCategory()
            if (response.isEmpty())
                onEmpty(true)
            else {
                onSuccess(response)
                onEmpty(false)
            }
        } catch (e: Exception) {
            onError(e.message.toString())
        } finally {
            onLoading(false)
        }
    }


    suspend fun getProducts(
        onLoading: (Boolean) -> Unit,
        onError: (String) -> Unit,
        onEmpty: (Boolean) -> Unit,
        onSuccess: (Array<ResponseItem>) -> Unit
    ) {
        onLoading(true)
        try {
            val response = client.getAllProducts()
            if (response.isNullOrEmpty())
                onEmpty(true)
            else {
                onSuccess(response)
                onEmpty(false)
            }
        } catch (e: Exception) {
            onError(e.message.toString())
        } finally {
            onLoading(false)
        }
    }

}