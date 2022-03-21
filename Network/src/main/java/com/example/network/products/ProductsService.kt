package com.example.network.products

import com.example.models.products.ProductsResponse
import com.example.models.products.ResponseItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsService {

    @GET("products")
    suspend fun getAllProducts(): Array<ResponseItem>

    @GET("products/{id}")
    suspend fun getSpecificProduct(@Path("id") id: Int): ResponseItem

    @GET("'products?limit={limit}")
    suspend fun getLimitedProducts(@Path("limit") limit: Int): ProductsResponse

    @GET("products/categories")
    suspend fun getProductsCategory(): Array<String>

}