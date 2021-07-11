package com.github.algamza.bucketplace.repository.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoginEntity(
    @PrimaryKey
    val id: Int,
    val login: Boolean
)
