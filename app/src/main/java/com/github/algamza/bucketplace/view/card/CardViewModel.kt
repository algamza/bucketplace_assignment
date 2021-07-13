package com.github.algamza.bucketplace.view.card

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.algamza.bucketplace.domain.usecase.CardUseCase

class CardViewModel @ViewModelInject constructor(
    private val cardUseCase: CardUseCase
) : ViewModel() {
    private val _cardCallbackObj: MutableLiveData<CardCallback> = MutableLiveData()
    private val _user: MutableLiveData<User> = MutableLiveData()
    private val _card: MutableLiveData<Card> = MutableLiveData()
    val cardCallbackObj: LiveData<CardCallback>
        get() = _cardCallbackObj
    val user: LiveData<User>
        get() = _user
    val card: LiveData<Card>
        get() = _card
    lateinit var recommends: LiveData<List<Recommend>>

    fun updateCard(id: Int) {
        recommends = Transformations.map(cardUseCase.getCardDetail(id)) {
            _user.postValue(User(it.user.id, it.user.nickname, it.user.introduction))
            _card.postValue(Card(it.card.id, it.card.user_id, it.card.img_url, it.card.description))
            it.recommend_cards.map { Recommend(recommendCallback, it.id, it.user_id, it.img_url, it.description) }
        }
    }

    private var recommendCallback = object: Recommend.Callback {
        override fun onClickCard(id: Int) {
            _cardCallbackObj.postValue(CardCallback(id))
        }
    }

    data class CardCallback(val id: Int)

    data class Recommend(
        var callback: Callback,
        val id: Int,
        val user_id: Int,
        val img_url: String,
        val description: String
    ) {
        interface Callback {
            fun onClickCard(id: Int)
        }
    }

    data class Card(
        val id: Int,
        val user_id: Int,
        val img_url: String,
        val description: String
    )

    data class User(
        val id: Int,
        val nickname: String,
        val introduction: String
    )
}