package com.github.algamza.bucketplace.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.algamza.bucketplace.data.local.entities.*
import com.github.algamza.bucketplace.data.local.common.Converters
import com.github.algamza.bucketplace.data.local.dao.CardDao

@Database(entities = [
    CardEntity::class,
    UserEntity::class,
    HomeEntity::class,
    CardRecommendEntity::class,
    LoginEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
}