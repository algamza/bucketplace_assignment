package com.github.algamza.bucketplace.domain.model

data class Card(
    val id: Int,
    val user_id: Int,
    val img_url: String,
    val description: String
)