package com.github.algamza.bucketplace.view

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.github.algamza.bucketplace.domain.usecase.UserUseCase

class MainViewModel @ViewModelInject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {
    val login: LiveData<Boolean> = userUseCase.isLogin().asLiveData()
    fun logout() = userUseCase.requestLogout()
}