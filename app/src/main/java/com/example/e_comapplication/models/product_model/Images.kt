package com.example.e_comapplication.models.product_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Images (
    val images: List<Image>
): Parcelable