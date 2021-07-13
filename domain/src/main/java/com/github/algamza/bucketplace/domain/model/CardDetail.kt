package com.github.algamza.bucketplace.domain.model

data class CardDetail(
    val card: Card,
    val user: User,
    val recommend_cards: List<Card>,
)