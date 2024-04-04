package com.example.muliplefragmentsoneactivity.Models

    data class Product(
val id: Int,
val title: String,
val description: String,
val price: Double,
val discountPercentage: Double,
val rating: Double,
val stock: Int,
val brand: String,
val category: String,
val thumbnail: String,
val images: List<String>
)
data class LoginResponse(
    val token: String
)