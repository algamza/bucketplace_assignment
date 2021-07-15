package com.github.algamza.bucketplace.domain.usecase

import com.github.algamza.bucketplace.domain.model.Home
import com.github.algamza.bucketplace.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val repository: Repository){
    fun getHome() : Flow<Home> = repository.getHome()
}