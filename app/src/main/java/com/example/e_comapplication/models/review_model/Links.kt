package com.example.e_comapplication.models.review_model

data class Links(
    val collection: List<Collection>,
    val reviewer: List<Reviewer>,
    val self: List<Self>,
    val up: List<Up>
)