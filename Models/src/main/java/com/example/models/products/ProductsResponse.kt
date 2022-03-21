package com.example.models.products

import com.squareup.moshi.Json

data class ProductsResponse(
	@Json(name = "Response")
	val response: Array<ResponseItem>
)

data class Rating(

	@Json(name = "rate")
	val rate: Double? = null,

	@Json(name = "count")
	val count: Int? = null
)

data class ResponseItem(

	@Json(name = "image")
	val image: String? = null,

	@Json(name = "price")
	val price: Double? = null,

	@Json(name = "rating")
	val rating: Rating? = null,

	@Json(name = "description")
	val description: String? = null,

	@Json(name = "id")
	val id: Int? = null,

	@Json(name = "title")
	val title: String? = null,

	@Json(name = "category")
	val category: String? = null
)
