package com.kldaji.loanclientmanagement.di

import android.content.Context
import androidx.room.Room
import com.kldaji.loanclientmanagement.LoanClientManagementDatabase
import com.kldaji.loanclientmanagement.model.data.UriListTypeConverter
import com.kldaji.loanclientmanagement.model.local.client.ClientDao
import com.kldaji.loanclientmanagement.model.local.recentSearchWord.RecentSearchWordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalDatabaseModule {
    @Provides
    fun provideLoanClientManagementDatabase(@ApplicationContext context: Context): LoanClientManagementDatabase =
        Room.databaseBuilder(context,
            LoanClientManagementDatabase::class.java,
            "loanClientManagement.db")
            .addTypeConverter(UriListTypeConverter())
            .build()

    @Provides
    fun provideClientDao(loanClientManagementDatabase: LoanClientManagementDatabase): ClientDao =
        loanClientManagementDatabase.clientDao()

    @Provides
    fun provideRecentSearchWordDao(loanClientManagementDatabase: LoanClientManagementDatabase): RecentSearchWordDao =
        loanClientManagementDatabase.recentSearchWordDao()
}
