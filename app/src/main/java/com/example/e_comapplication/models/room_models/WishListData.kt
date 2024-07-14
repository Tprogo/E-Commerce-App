package com.example.e_comapplication.models.room_models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey


@Entity(tableName = "Wishlist")
data class WishListData(

    @PrimaryKey
    val wishProductId: Long,
    @ColumnInfo(name = "product_name")
    val wishName: String,
    @ColumnInfo(name = "product_image")
    val wishImage: String,
    @ColumnInfo(name = "price")
    val wishPrice: String,
    @ColumnInfo(name = "rating")
    val wishRating: String
)
