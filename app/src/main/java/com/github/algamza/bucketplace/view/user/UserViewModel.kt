package com.github.algamza.bucketplace.view.user

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.github.algamza.bucketplace.repository.Repository

class UserViewModel @ViewModelInject constructor(
    private val repository: Repository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    lateinit var user: LiveData<User>

    fun updateUser(id: Int) {
        user = Transformations.map(repository.getUser(id)) {
            User(it.nickname, it.introduction)
        }
    }

    data class User(
        val nickname: String,
        val introduction: String
    )
}