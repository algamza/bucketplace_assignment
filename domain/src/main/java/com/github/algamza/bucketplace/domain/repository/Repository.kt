package com.github.algamza.bucketplace.domain.repository

import androidx.lifecycle.LiveData
import com.github.algamza.bucketplace.domain.model.Card
import com.github.algamza.bucketplace.domain.model.CardDetail
import com.github.algamza.bucketplace.domain.model.Home
import com.github.algamza.bucketplace.domain.model.User

interface Repository {
    fun getHome() : LiveData<Home>
    fun getFeeds() : LiveData<List<Card>>
    fun getCardDetail(id: Int) : LiveData<CardDetail>
    fun requestCards(page: Int, per: Int)
    fun isLogin() : LiveData<Boolean>
    fun getUser(id: Int) : LiveData<User>
    fun requestSignUp(nickname: String, introduction: String, password: String)
    fun requestSignIn(nickname: String, password: String)
    fun requestLogout()
}