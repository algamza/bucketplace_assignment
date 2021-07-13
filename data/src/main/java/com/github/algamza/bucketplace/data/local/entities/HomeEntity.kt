package com.github.algamza.bucketplace.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HomeEntity(
    @PrimaryKey
    val id: Int,
    val popular_cards: List<CardEntity>,
    val popular_users: List<UserEntity>
)