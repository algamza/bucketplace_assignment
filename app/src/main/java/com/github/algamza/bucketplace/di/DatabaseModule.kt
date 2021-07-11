package com.github.algamza.bucketplace.di

import android.content.Context
import androidx.room.Room
import com.github.algamza.bucketplace.repository.local.LocalDatabase
import com.github.algamza.bucketplace.repository.local.dao.CardDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : LocalDatabase {
        return Room.databaseBuilder(context, LocalDatabase::class.java, "local.db").build()
    }

    @Provides
    fun provideCardDao(database: LocalDatabase) : CardDao { return database.cardDao() }
}