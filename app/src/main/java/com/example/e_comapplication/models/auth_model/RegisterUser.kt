package com.example.e_comapplication.models.auth_model

data class RegisterUser(
    val email: String,
    val password: String,
    val first_name: String,
    val last_name: String
)

