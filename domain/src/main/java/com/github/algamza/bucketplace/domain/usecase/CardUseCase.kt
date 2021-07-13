package com.github.algamza.bucketplace.domain.usecase

import androidx.lifecycle.LiveData
import com.github.algamza.bucketplace.domain.model.Card
import com.github.algamza.bucketplace.domain.model.CardDetail
import com.github.algamza.bucketplace.domain.repository.Repository
import javax.inject.Inject

class CardUseCase @Inject constructor(private val repository: Repository) {
    fun getFeeds() : LiveData<List<Card>> = repository.getFeeds()
    fun getCardDetail(id: Int) : LiveData<CardDetail> = repository.getCardDetail(id)
    fun requestCards(page: Int, per: Int) = repository.requestCards(page, per)
}