package com.example.e_comapplication.models.product_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Image(
    val alt: String,
    val date_created: String,
    val date_created_gmt: String,
    val date_modified: String,
    val date_modified_gmt: String,
    val id: Int,
    val name: String,
    val src: String
): Parcelable