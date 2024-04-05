package com.example.muliplefragmentsoneactivity.Models
data class UserModel(
    val username: String,
    val password: String
)


data class LoginResponse(
    val token: String
)