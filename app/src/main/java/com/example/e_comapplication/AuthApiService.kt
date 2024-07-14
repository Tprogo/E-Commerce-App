package com.example.e_comapplication

import com.example.e_comapplication.models.auth_model.LoginResponse
import com.example.e_comapplication.models.auth_model.LoginUser
import com.example.e_comapplication.models.auth_model.RegisterResponse
import com.example.e_comapplication.models.auth_model.RegisterUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApiService {

    @POST("users/")
    suspend fun registerUser(@Body user: RegisterUser): Response<RegisterResponse>

    @POST("auth/")
    suspend fun loginUser(@Body user: LoginUser): Response<LoginResponse>




}