package com.github.algamza.bucketplace.domain.usecase

import androidx.lifecycle.LiveData
import com.github.algamza.bucketplace.domain.model.User
import com.github.algamza.bucketplace.domain.repository.Repository
import javax.inject.Inject

class UserUseCase @Inject constructor(private val repository: Repository) {
    fun isLogin() : LiveData<Boolean> = repository.isLogin()
    fun getUser(id: Int) : LiveData<User> = repository.getUser(id)
    fun requestSignUp(nickname: String, introduction: String, password: String) = repository.requestSignUp(nickname, introduction, password)
    fun requestSignIn(nickname: String, password: String) = repository.requestSignIn(nickname, password)
    fun requestLogout() = repository.requestLogout()
}