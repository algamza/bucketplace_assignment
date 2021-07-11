package com.github.algamza.bucketplace.view.signin

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.github.algamza.bucketplace.repository.Repository

class SignInViewModel @ViewModelInject constructor(
    private val repository: Repository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _nickname: MutableLiveData<String> = MutableLiveData("")
    private val _password: MutableLiveData<String> = MutableLiveData("")
    val nickname: LiveData<String>
        get() = _nickname
    val password: LiveData<String>
        get() = _password
    val login : LiveData<Boolean> = Transformations.map(repository.login) { it }

    fun setNickName(nickName: String) = _nickname.postValue(nickName)
    fun setPassword(password: String) = _password.postValue(password)

    fun onClickSignIn(nickName: String, password: String) {
        repository.requestSignIn(nickName, password)
    }
}