package com.github.algamza.bucketplace.data.remote.common

import retrofit2.Response

abstract class BaseRepo {
    suspend fun <Any> apiRequest(call: suspend () -> Response<Any>): Any {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val message = StringBuilder()
            message.append("ERROR Code : ${response.code()}")
            throw Exception(message.toString())
        }
    }
}