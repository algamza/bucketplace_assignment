package com.github.algamza.bucketplace.di

import com.github.algamza.bucketplace.repository.Repository
import com.github.algamza.bucketplace.repository.local.dao.CardDao
import com.github.algamza.bucketplace.repository.remote.RemoteRepo
import com.github.algamza.bucketplace.repository.remote.api.RemoteService
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
        return Repository(cardDao, remoteRepo)
    }
}