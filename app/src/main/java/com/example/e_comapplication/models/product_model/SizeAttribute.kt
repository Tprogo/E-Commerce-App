package com.example.e_comapplication.models.product_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class SizeAttribute(
    val sizeAttributes: List<Attribute>
): Parcelable
