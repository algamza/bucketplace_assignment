package com.github.algamza.bucketplace.repository.remote.entities

data class SignUpRequest(
    val nickname: String,
    val introduction: String,
    val pwd: String
)

data class SignInRequest(
    val nickname: String,
    val pwd: String
)