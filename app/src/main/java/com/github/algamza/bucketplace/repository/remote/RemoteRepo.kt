package com.github.algamza.bucketplace.repository.remote

import com.github.algamza.bucketplace.repository.remote.api.RemoteService
import com.github.algamza.bucketplace.repository.remote.common.BaseRepo
import com.github.algamza.bucketplace.repository.remote.entities.*

class RemoteRepo(private val remoteService: RemoteService) : BaseRepo() {

    suspend fun requestSignUp(nickname: String, introduction: String, pwd: String) : ResultResponse =
        apiRequest { remoteService.requestSignUp(SignUpRequest(nickname, introduction, pwd)) }

    suspend fun requestSignIn(nickname: String, pwd: String) : ResultResponse =
        apiRequest { remoteService.requestSignIn(SignInRequest(nickname, pwd)) }

    suspend fun requestHome() : HomeResponse =
        apiRequest { remoteService.requestHome() }

    suspend fun requestCard(page: Int, per: Int) : CardResponse =
        apiRequest { remoteService.requestCards(page, per) }

    suspend fun requestCardDetail(id: Int) : CardDetailResponse =
        apiRequest { remoteService.requestCardDetail(id) }

    suspend fun requestUserDetail(id: Int) : UserDetailResponse =
        apiRequest { remoteService.requestUserDetail(id) }
}