package com.github.algamza.bucketplace.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.algamza.bucketplace.repository.local.common.Converters
import com.github.algamza.bucketplace.repository.local.dao.CardDao
import com.github.algamza.bucketplace.repository.local.entities.*

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