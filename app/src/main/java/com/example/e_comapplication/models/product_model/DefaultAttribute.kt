package com.example.e_comapplication.models.product_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class DefaultAttribute(
    val id: Int,
    val name: String,
    val option: String
): Parcelable