package com.example.muliplefragmentsoneactivity.helper

import com.example.muliplefragmentsoneactivity.Models.Product
import com.example.muliplefragmentsoneactivity.Models.UserModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>

    @POST("auth/login")
    suspend fun login(@Body loginRequest: UserModel): LoginResponse
}

data class LoginResponse(
    val token: String
)