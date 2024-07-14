package com.example.e_comapplication.models.product_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Links(
    val collection: List<Collection>,
    val self: List<Self>
): Parcelable