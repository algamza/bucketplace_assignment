package com.github.algamza.bucketplace.data.di

import com.github.algamza.bucketplace.data.repository.RepositoryImpl
import com.github.algamza.bucketplace.data.local.dao.CardDao
import com.github.algamza.bucketplace.data.remote.RemoteRepo
import com.github.algamza.bucketplace.data.remote.api.RemoteService
import com.github.algamza.bucketplace.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRemoteHelper(remoteService: RemoteService) : RemoteRepo {
        return RemoteRepo(remoteService)
    }

    @Provides
    @Singleton
    fun provideRepository(cardDao: CardDao, remoteRepo: RemoteRepo) : Repository {
        return RepositoryImpl(cardDao, remoteRepo)
    }
}