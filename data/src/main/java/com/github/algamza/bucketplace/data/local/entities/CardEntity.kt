package com.github.algamza.bucketplace.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CardEntity(
    @PrimaryKey
    val id: Int,
    val user_id: Int,
    val img_url: String,
    val description: String
)
