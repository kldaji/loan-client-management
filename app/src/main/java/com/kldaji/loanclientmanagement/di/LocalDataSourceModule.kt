package com.kldaji.loanclientmanagement.di

import com.kldaji.loanclientmanagement.model.local.client.ClientDao
import com.kldaji.loanclientmanagement.model.local.client.ClientLocalDataSource
import com.kldaji.loanclientmanagement.model.local.client.ClientLocalDataSourceImpl
import com.kldaji.loanclientmanagement.model.local.recentSearchWord.RecentSearchWordDao
import com.kldaji.loanclientmanagement.model.local.recentSearchWord.RecentSearchWordLocalDataSource
import com.kldaji.loanclientmanagement.model.local.recentSearchWord.RecentSearchWordLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object LocalDataSourceModule {

    @Provides
    fun provideClientLocalDataSource(clientDao: ClientDao): ClientLocalDataSource =
        ClientLocalDataSourceImpl(clientDao)

    @Provides
    fun provideRecentSearchWordDataSource(recentSearchWordDao: RecentSearchWordDao): RecentSearchWordLocalDataSource =
        RecentSearchWordLocalDataSourceImpl(recentSearchWordDao)
}
