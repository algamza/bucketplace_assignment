package com.github.algamza.bucketplace.model

data class Card(
    val id: Int,
    val user_id: Int,
    val img_url: String,
    val description: String
)

data class User(
    val id: Int,
    val nickname: String,
    val introduction: String
)

data class Home(
    val popular_cards: ArrayList<Card>,
    val popular_users: ArrayList<User>
)

data class CardDetail(
    val card: Card,
    val user: User,
    val recommend_cards: List<Card>,
)