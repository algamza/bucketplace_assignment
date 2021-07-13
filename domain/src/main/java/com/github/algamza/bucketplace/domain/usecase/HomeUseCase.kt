package com.github.algamza.bucketplace.domain.usecase

import androidx.lifecycle.LiveData
import com.github.algamza.bucketplace.domain.model.Home
import com.github.algamza.bucketplace.domain.repository.Repository
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val repository: Repository){
    fun getHome() : LiveData<Home> = repository.getHome()
}