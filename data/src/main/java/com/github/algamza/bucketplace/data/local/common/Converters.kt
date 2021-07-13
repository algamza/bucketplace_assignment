package com.github.algamza.bucketplace.data.local.common

import androidx.room.TypeConverter
import com.github.algamza.bucketplace.data.local.entities.CardEntity
import com.github.algamza.bucketplace.data.local.entities.UserEntity
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun listToJsonCard(value: List<CardEntity>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToListCard(value: String) = Gson().fromJson(value, Array<CardEntity>::class.java).toList()

    @TypeConverter
    fun listToJsonUser(value: List<UserEntity>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToListUser(value: String) = Gson().fromJson(value, Array<UserEntity>::class.java).toList()
}