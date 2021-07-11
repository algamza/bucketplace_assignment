package com.github.algamza.bucketplace.repository.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CardRecommendEntity(
    @PrimaryKey
    val id: Int,
    val recommend_cards: List<CardEntity>
)