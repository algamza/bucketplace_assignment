package com.github.algamza.bucketplace.domain.repository

import com.github.algamza.bucketplace.domain.model.Card
import com.github.algamza.bucketplace.domain.model.CardDetail
import com.github.algamza.bucketplace.domain.model.Home
import com.github.algamza.bucketplace.domain.model.User
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getHome() : Flow<Home>
    fun getFeeds() : Flow<List<Card>>
    fun getCardDetail(id: Int) : Flow<CardDetail>
    fun requestCards(page: Int, per: Int)
    fun isLogin() : Flow<Boolean>
    fun getUser(id: Int) : Flow<User>
    fun requestSignUp(nickname: String, introduction: String, password: String)
    fun requestSignIn(nickname: String, password: String)
    fun requestLogout()
}