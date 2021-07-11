package com.github.algamza.bucketplace.view

import androidx.constraintlayout.widget.ConstraintSet
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.github.algamza.bucketplace.repository.Repository

class MainViewModel @ViewModelInject constructor(
    private val repository: Repository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val login: LiveData<Boolean> = Transformations.map(repository.login) { it }

    fun logout() = repository.requestLogout()

}