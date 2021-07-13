package com.github.algamza.bucketplace.view.feed

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.algamza.bucketplace.domain.usecase.CardUseCase


class FeedViewModel @ViewModelInject constructor(
    private val cardUseCase: CardUseCase
) : ViewModel() {
    private val _cardCallbackObj: MutableLiveData<CardCallback> = MutableLiveData()
    val cardCallbackObj: LiveData<CardCallback>
        get() = _cardCallbackObj
    var feed: LiveData<List<FeedData>> = Transformations.map(cardUseCase.getFeeds()) {
        it.map { FeedData(callback, it.id, it.user_id, it.img_url, it.description) }
    }

    private var callback = object: FeedData.Callback {
        override fun onClickFeed(id: Int) {
            _cardCallbackObj.postValue(CardCallback(id))
        }
    }

    fun requestFeed(page: Int, per: Int) = cardUseCase.requestCards(page, per)

    data class CardCallback(val id: Int)
}