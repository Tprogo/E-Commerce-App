package com.example.e_comapplication.models

data class User(
    val ID: String,
    val display_name: String,
    val user_activation_key: String,
    val user_email: String,
    val user_level: Int,
    val user_login: String,
    val user_nicename: String,
    val user_registered: String,
    val user_status: String,
    val user_url: String
)