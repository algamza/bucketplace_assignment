package com.github.algamza.bucketplace.view.feed

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.github.algamza.bucketplace.repository.Repository


class FeedViewModel @ViewModelInject constructor(
    private val repository: Repository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _cardCallbackObj: MutableLiveData<CardCallback> = MutableLiveData()
    val cardCallbackObj: LiveData<CardCallback>
        get() = _cardCallbackObj
    var feed: LiveData<List<FeedData>> = Transformations.map(repository.feed) {
        it.map { FeedData(callback, it.id, it.user_id, it.img_url, it.description) }
    }

    private var callback = (object: FeedData.Callback {
        override fun onClickFeed(id: Int) {
            _cardCallbackObj.postValue(CardCallback(id))
        }
    })

    fun requestFeed(page: Int, per: Int) {
        repository.requestCards(page, per)
    }

    data class CardCallback(val id: Int)
}