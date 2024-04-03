package com.example.muliplefragmentsoneactivity.helper

import com.example.muliplefragmentsoneactivity.Models.Product
import com.example.muliplefragmentsoneactivity.Models.UserModel


class Repository(private val apiService: ApiService) {
    suspend fun login(username: String, password: String): LoginResponse {
        val userModel = UserModel(username, password)
        return apiService.login(userModel)
    }

    suspend fun getProducts(): List<Product> {
        return apiService.getProducts()
    }
}