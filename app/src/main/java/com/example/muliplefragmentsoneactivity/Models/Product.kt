package com.example.muliplefragmentsoneactivity.Models

data class Product(
val id: Int,
val title: String,
val description: String,
val price: Double,
val brand: String,
val category: String,
val images: List<String>
)
data class ProductResponse(val products: List<Product>)
