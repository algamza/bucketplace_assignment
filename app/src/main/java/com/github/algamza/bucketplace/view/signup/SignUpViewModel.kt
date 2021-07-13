package com.github.algamza.bucketplace.view.signup

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.algamza.bucketplace.domain.usecase.UserUseCase

class SignUpViewModel @ViewModelInject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {
    private val _nickname: MutableLiveData<String> = MutableLiveData("")
    private val _introduction: MutableLiveData<String> = MutableLiveData("")
    private val _password: MutableLiveData<String> = MutableLiveData("")
    val nickname: LiveData<String>
        get() = _nickname
    val introduction: LiveData<String>
        get() = _introduction
    val password: LiveData<String>
        get() = _password
    val login : LiveData<Boolean> = Transformations.map(userUseCase.isLogin()) { it }

    fun setNickName(nickName: String) = _nickname.postValue(nickName)
    fun setIntroduction(introduction: String) = _introduction.postValue(introduction)
    fun setPassword(password: String) = _password.postValue(password)

    fun onClickSignUp(nickName: String, introduction: String, password: String)
        = userUseCase.requestSignUp(nickName, introduction, password)

}