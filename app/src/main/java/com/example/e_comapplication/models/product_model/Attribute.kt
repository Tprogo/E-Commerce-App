package com.example.e_comapplication.models.product_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Collections

@Parcelize
data class Attribute(
    val id: Int,
    val name: String,
    val options: List<String> = Collections.emptyList(),
    val position: Int,
    val slug: String,
    val variation: Boolean,
    val visible: Boolean
): Parcelable