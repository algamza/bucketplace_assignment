package com.github.algamza.bucketplace.view.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.github.algamza.bucketplace.domain.usecase.HomeUseCase

class HomeViewModel @ViewModelInject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {
    private val _userCallbackObj: MutableLiveData<UserCallback> = MutableLiveData()
    private val _cardCallbackObj: MutableLiveData<CardCallback> = MutableLiveData()
    private val _users: MutableLiveData<List<UserData>> = MutableLiveData()
    val userCallbackObj: LiveData<UserCallback>
        get() = _userCallbackObj
    val cardCallbackObj: LiveData<CardCallback>
        get() = _cardCallbackObj
    val users: LiveData<List<UserData>>
        get() = _users
    var cards: LiveData<List<CardData>> = homeUseCase.getHome().asLiveData().map {
        _users.postValue(it.popular_users.map { UserData(userCallback, it.id, it.nickname, it.introduction) })
        it.popular_cards.map { CardData(cardCallback, it.id, it.user_id, it.img_url, it.description) }
    }

    private var userCallback = object: UserData.Callback {
        override fun onClickUser(id: Int) {
            _userCallbackObj.postValue(UserCallback(id))
        }
    }

    private var cardCallback = object: CardData.Callback {
        override fun onClickCard(id: Int) {
            _cardCallbackObj.postValue(CardCallback(id))
        }
    }

    data class UserCallback(val id: Int)
    data class CardCallback(val id: Int)
}