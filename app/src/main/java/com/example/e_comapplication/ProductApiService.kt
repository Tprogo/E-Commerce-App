package com.example.e_comapplication

import com.example.e_comapplication.models.product_model.ProductModel
import com.example.e_comapplication.models.review_model.ReviewModelItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApiService {

    @GET("products/")
    suspend fun getAllProducts(): Response<List<ProductModel>>

    @GET("products?orderby=popularity&order=desc")
    suspend fun getTopSellingPro(): Response<List<ProductModel>>

    @GET("products?orderby=rating&order=desc")
    suspend fun getTopRatedPro(): Response<List<ProductModel>>

    @GET("products/{id}")
    suspend fun getProdDetails(@Path("id") productId: Long): Response<ProductModel>

    @GET("products/reviews/")
    suspend fun getProdReviews(@Query("product") productId: Long): Response<List<ReviewModelItem>>

    @GET("products/")
    suspend fun getSearchData(@Query("search") keyword: String): Response<List<ProductModel>>





}