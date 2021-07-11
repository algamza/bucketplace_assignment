package com.github.algamza.bucketplace.repository.remote.entities


data class Card(
        val user_id: Int,
        val img_url: String,
        val description: String,
        val id: Int
)

data class User(
        val nickname: String,
        val introduction: String,
        val id: Int
)

data class HomeResponse(
        val popular_cards: ArrayList<Card>,
        val popular_users: ArrayList<User>,
        val ok: Boolean
)

data class CardResponse(
        val ok: Boolean,
        val cards: ArrayList<Card>
)

data class CardDetailResponse(
        val card: Card,
        val user: User,
        val recommend_cards: ArrayList<Card>,
        val ok: Boolean
)

data class UserDetailResponse(
        val ok: Boolean,
        val user: User
)

data class ResultResponse(
        val ok: Boolean,
        val msg: String
)



