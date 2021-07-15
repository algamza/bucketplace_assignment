package com.github.algamza.bucketplace.view.user

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.github.algamza.bucketplace.domain.usecase.UserUseCase

class UserViewModel @ViewModelInject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {
    lateinit var user: LiveData<User>

    fun updateUser(id: Int) {
        user = userUseCase.getUser(id).asLiveData().map { User(it.nickname, it.introduction) }
    }

    data class User(
        val nickname: String,
        val introduction: String
    )
}