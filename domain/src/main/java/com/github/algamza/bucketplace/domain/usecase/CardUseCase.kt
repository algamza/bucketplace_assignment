package com.github.algamza.bucketplace.domain.usecase

import com.github.algamza.bucketplace.domain.model.Card
import com.github.algamza.bucketplace.domain.model.CardDetail
import com.github.algamza.bucketplace.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardUseCase @Inject constructor(private val repository: Repository) {
    fun getFeeds() : Flow<List<Card>> = repository.getFeeds()
    fun getCardDetail(id: Int) : Flow<CardDetail> = repository.getCardDetail(id)
    fun requestCards(page: Int, per: Int) = repository.requestCards(page, per)
}