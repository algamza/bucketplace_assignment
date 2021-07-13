package com.github.algamza.bucketplace.domain.model

data class Home(
    val popular_cards: ArrayList<Card>,
    val popular_users: ArrayList<User>
)