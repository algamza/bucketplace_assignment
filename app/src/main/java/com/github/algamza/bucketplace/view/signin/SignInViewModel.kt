package com.github.algamza.bucketplace.view.signin

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.github.algamza.bucketplace.domain.usecase.UserUseCase

class SignInViewModel @ViewModelInject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {
    private val _nickname: MutableLiveData<String> = MutableLiveData("")
    private val _password: MutableLiveData<String> = MutableLiveData("")
    val nickname: LiveData<String>
        get() = _nickname
    val password: LiveData<String>
        get() = _password
    val login : LiveData<Boolean> = userUseCase.isLogin().asLiveData()

    fun setNickName(nickName: String) = _nickname.postValue(nickName)
    fun setPassword(password: String) = _password.postValue(password)

    fun onClickSignIn(nickName: String, password: String) = userUseCase.requestSignIn(nickName, password)

}