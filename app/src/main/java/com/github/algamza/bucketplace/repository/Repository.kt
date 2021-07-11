package com.github.algamza.bucketplace.repository

import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.github.algamza.bucketplace.model.Card
import com.github.algamza.bucketplace.model.CardDetail
import com.github.algamza.bucketplace.model.Home
import com.github.algamza.bucketplace.model.User
import com.github.algamza.bucketplace.repository.local.dao.CardDao
import com.github.algamza.bucketplace.repository.local.entities.*
import com.github.algamza.bucketplace.repository.remote.RemoteRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class Repository @Inject constructor(
    private var cardDao: CardDao,
    private var remoteRepo: RemoteRepo) {
    var home: LiveData<Home> = Transformations.map(cardDao.getHomeEntity(0)) {
        var cards = ArrayList<Card>()
        var users = ArrayList<User>()
        if ( it == null ) return@map Home(cards, users)
        if ( it.popular_cards != null ) {
            for ( card in it.popular_cards ) {
                cards.add(Card(card.id, card.user_id, card.img_url, card.description))
            }
        }
        if ( it.popular_users != null ) {
            for ( user in it.popular_users ) {
                users.add(User(user.id, user.nickname, user.introduction))
            }
        }
        Home(cards, users)
    }
        get() {
            requestHome()
            return field
        }

    var feed: LiveData<List<Card>> = Transformations.map(cardDao.getCardEntities()) {
        it.map { Card(it.id, it.user_id, it.img_url, it.description) }
    }
        get() {
            requestCards(1, 20)
            return field
        }

    var login: LiveData<Boolean> = Transformations.map(cardDao.getLogInEntity(0)) { when(it) {
        null -> false
        else -> it.login
    } }

    fun getUser(id: Int) : LiveData<User> {
        CoroutineScope(Dispatchers.IO).launch {
            var respone = remoteRepo.requestUserDetail(id)
            cardDao.insertUserEntity(UserEntity(respone.user.id, respone.user.nickname, respone.user.introduction))
        }
       return cardDao.getUserDetailEntity(id).map {
           when(it) {
               null -> User(0, "", "")
               else -> User(it.id, it.nickname, it.introduction)
           }
       }
    }

    fun getCard(id: Int) : LiveData<CardDetail> {
        CoroutineScope(Dispatchers.IO).launch {
            var respone = remoteRepo.requestCardDetail(id)
            cardDao.insertCardEntity(CardEntity(respone.card.id, respone.card.user_id,
                respone.card.img_url, respone.card.description))
            cardDao.insertUserEntity(UserEntity(respone.user.id, respone.user.nickname,
                respone.user.introduction))
            cardDao.insertCardRecommendEntities(respone.recommend_cards.map {
                CardRecommendEntity(respone.card.id, respone.recommend_cards.map {
                    CardEntity(it.id, it.user_id, it.img_url, it.description) }) })
        }
        return cardDao.getCardDetailEntity(id).map {
            when(it) {
                null -> CardDetail(Card(0, 0, "", ""),
                    User(0, "", ""), ArrayList())
                else -> {
                    CardDetail(Card(it.card.id, it.card.user_id, it.card.img_url, it.card.description),
                        when(it.user) {
                            null -> User(0, "", "")
                            else -> User(it.user!!.id, it.user!!.nickname, it.user!!.introduction)
                        },
                        when(it.recommend) {
                            null -> ArrayList()
                            else -> {
                                it.recommend!!.recommend_cards.map { Card(it.id, it.user_id, it.img_url, it.description) }
                            }
                        })
                }
            }
        }
    }

    private fun requestHome() {
        CoroutineScope(Dispatchers.IO).launch {
            var respone = remoteRepo.requestHome()
            if ( respone == null ) return@launch
            cardDao.insertHomeEntity(HomeEntity(0, respone.popular_cards.map {
                CardEntity(it.id, it.user_id, it.img_url, it.description)
            }, respone.popular_users.map {
                UserEntity(it.id, it.nickname, it.introduction)
            }))
        }
    }

    fun requestCards(page: Int, per: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            var respone = remoteRepo.requestCard(page, per)
            if ( respone == null ) return@launch
            cardDao.insertCardEntities(respone.cards.map {
                CardEntity(it.id, it.user_id, it.img_url, it.description)
            })
        }
    }

    fun requestSignUp(nickname: String, introduction: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            var respone = remoteRepo.requestSignUp(nickname, introduction, password)
            if ( respone == null ) return@launch
            cardDao.insertLogInEntity(when(respone.ok) {
                true -> LoginEntity(0, true)
                false -> LoginEntity(1, false)
            })
        }
    }

    fun requestSignIn(nickname: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            var respone = remoteRepo.requestSignIn(nickname, password)
            if ( respone == null ) return@launch
            cardDao.insertLogInEntity(when(respone.ok) {
                true -> LoginEntity(0, true)
                false -> LoginEntity(1, false)
            })
        }
    }

    fun requestLogout() {
        CoroutineScope(Dispatchers.IO).launch {
            cardDao.insertLogInEntity(LoginEntity(0, false))
        }
    }
}