package com.github.algamza.bucketplace.view.signup

import androidx.constraintlayout.widget.ConstraintSet
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.github.algamza.bucketplace.repository.Repository

class SignUpViewModel @ViewModelInject constructor(
    private val repository: Repository,
    @Assisted private val savedStateHandle: SavedStateHandle
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
    val login : LiveData<Boolean> = Transformations.map(repository.login) { it }

    fun setNickName(nickName: String) = _nickname.postValue(nickName)
    fun setIntroduction(introduction: String) = _introduction.postValue(introduction)
    fun setPassword(password: String) = _password.postValue(password)

    fun onClickSignUp(nickName: String, introduction: String, password: String) {
        repository.requestSignUp(nickName, introduction, password)
    }
}