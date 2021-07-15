package com.github.algamza.bucketplace.domain.usecase

import com.github.algamza.bucketplace.domain.model.User
import com.github.algamza.bucketplace.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCase @Inject constructor(private val repository: Repository) {
    fun isLogin() : Flow<Boolean> = repository.isLogin()
    fun getUser(id: Int) : Flow<User> = repository.getUser(id)
    fun requestSignUp(nickname: String, introduction: String, password: String) = repository.requestSignUp(nickname, introduction, password)
    fun requestSignIn(nickname: String, password: String) = repository.requestSignIn(nickname, password)
    fun requestLogout() = repository.requestLogout()
}