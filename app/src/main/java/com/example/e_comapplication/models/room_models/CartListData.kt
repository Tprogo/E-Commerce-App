package com.example.e_comapplication.models.room_models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "CartList")
data class CartListData(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "product_name")
    val name: String,
    @ColumnInfo(name = "product_image")
    val image: String,
    @ColumnInfo(name = "price")
    val price: String,
    @ColumnInfo(name = "rating")
    val rating: String
)
