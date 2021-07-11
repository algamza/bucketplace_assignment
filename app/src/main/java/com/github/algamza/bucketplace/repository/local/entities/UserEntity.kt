package com.github.algamza.bucketplace.repository.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val nickname: String,
    val introduction: String
)