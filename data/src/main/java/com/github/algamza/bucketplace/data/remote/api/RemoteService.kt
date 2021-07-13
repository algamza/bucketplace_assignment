package com.github.algamza.bucketplace.data.remote.api

import com.github.algamza.bucketplace.data.remote.entities.*
import retrofit2.Response
import retrofit2.http.*

interface RemoteService {

    @POST("/users")
    suspend fun requestSignUp(@Body signUp: SignUpRequest) : Response<ResultResponse>

    @POST("/users/sign_in")
    suspend fun requestSignIn(@Body signIn: SignInRequest) : Response<ResultResponse>

    @GET("/home")
    suspend fun requestHome() : Response<HomeResponse>

    @GET("/cards?")
    suspend fun requestCards(
        @Query("page") page:Int,
        @Query("per") per:Int
    ) : Response<CardResponse>

    @GET("/cards/{id}")
    suspend fun requestCardDetail(
        @Path("id") id:Int
    ) : Response<CardDetailResponse>

    @GET("/users/{id}")
    suspend fun requestUserDetail(
        @Path("id") id:Int
    ) : Response<UserDetailResponse>
}