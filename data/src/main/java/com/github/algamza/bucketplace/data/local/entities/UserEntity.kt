package com.github.algamza.bucketplace.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val nickname: String,
    val introduction: String
)