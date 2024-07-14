package com.example.e_comapplication.models.auth_model

import com.example.e_comapplication.models.User

data class RegisterResponse(
    val id: String,
    val jwt: String,
    val message: String,
    val roles: List<String>,
    val success: Boolean,
    val user: User
)